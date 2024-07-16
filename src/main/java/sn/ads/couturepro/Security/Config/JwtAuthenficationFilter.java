package sn.ads.couturepro.Security.Config;

import sn.ads.couturepro.Security.Exceptions.SecurityExceptionHandler;
import sn.ads.couturepro.Security.Interfaces.IJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.ads.couturepro.Security.Services.UserServiceSecurity;
import sn.ads.couturepro.services.UserService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenficationFilter extends OncePerRequestFilter {

    private final IJwtService jwtService;
    private final UserServiceSecurity utilisateurService;
    private final SecurityExceptionHandler securityExceptionHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String utilisateurEmail;
        try {
            if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            utilisateurEmail = jwtService.extractUsername(jwt);

            if (StringUtils.isNotEmpty(utilisateurEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = utilisateurService.userDetailsService().loadUserByUsername(utilisateurEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Extraction des autorités du token
                    List<GrantedAuthority> authorities = jwtService.extractAuthoritiesFromToken(jwt);

                    // Création d'un objet d'authentification avec les autorités extraites
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Définir l'objet d'authentification dans le contexte de sécurité
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }

        }catch (Exception e){
            securityExceptionHandler.handleSecurityException(e, response);
        }
        filterChain.doFilter(request, response);
    }

}
