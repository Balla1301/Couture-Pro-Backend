package sn.ads.couturepro.Security.Interfaces;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface IJwtService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    List<GrantedAuthority> extractAuthoritiesFromToken(String jwt);
}
