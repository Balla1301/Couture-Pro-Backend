package sn.ads.couturepro.Security.Services;

import sn.ads.couturepro.Security.Interfaces.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService implements IJwtService {

    private static final String SECRET = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";

  /*  public String generateToken(UserDetails userDetails){

        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }*/

    public String generateToken(UserDetails userDetails) {
        /*Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<String> authorityStrings = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());*/

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                /*.claim("authorities", authorityStrings)*/ // Ajouter les autorités au token
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 604800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims=extractAllClaim(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public List<GrantedAuthority> extractAuthoritiesFromToken(String token) {
        Claims claims = extractAllClaim(token);

        if (claims.containsKey("authorities")) {
            List<String> authorityStrings = (List<String>) claims.get("authorities");
            return authorityStrings.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }


}
