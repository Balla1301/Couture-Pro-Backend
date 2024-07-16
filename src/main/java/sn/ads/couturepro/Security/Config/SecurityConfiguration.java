package sn.ads.couturepro.Security.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sn.ads.couturepro.Security.Services.UserServiceSecurity;
import sn.ads.couturepro.services.UserService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenficationFilter jwtAuthenficationFilter;

    private final UserServiceSecurity utilisateurService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/utilisateurs/auth/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authentificationProvider()).addFilterBefore(
                        jwtAuthenficationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();

    }

    @Bean
    public AuthenticationProvider authentificationProvider() throws Exception {
        try {
            DaoAuthenticationProvider authentifiactionProvider = new DaoAuthenticationProvider();
            authentifiactionProvider.setUserDetailsService(utilisateurService.userDetailsService());
            authentifiactionProvider.setPasswordEncoder(passwordEncoder());
            return authentifiactionProvider;
        } catch (Exception e) {
            throw new Exception("Error in authentication provider: " + e.getMessage());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws Exception {
        try {
            return new BCryptPasswordEncoder();
        } catch (Exception e) {
            throw new Exception("Error creating password encoder: " + e.getMessage());
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        try {
            return config.getAuthenticationManager();
        } catch (Exception e) {
            throw new Exception("Error getting authentication manager: " + e.getMessage());
        }
    }


}
