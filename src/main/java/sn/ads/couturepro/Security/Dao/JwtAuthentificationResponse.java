package sn.ads.couturepro.Security.Dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthentificationResponse {
    private UserResponseDto userConnect;
    private String token;
}
