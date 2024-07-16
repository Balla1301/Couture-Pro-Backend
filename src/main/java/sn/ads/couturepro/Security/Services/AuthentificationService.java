package sn.ads.couturepro.Security.Services;

import sn.ads.couturepro.Security.Dao.JwtAuthentificationResponse;
import sn.ads.couturepro.Security.Dao.RefreshTokenRequest;
import sn.ads.couturepro.Security.Dao.SignInRequest;
import sn.ads.couturepro.Security.Interfaces.IAuthentificationService;
import sn.ads.couturepro.Security.Interfaces.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.convertisseurs.UserConverter;
import sn.ads.couturepro.entities.Utilisateur;
import sn.ads.couturepro.exceptions.RessourceNotFoundException;
import sn.ads.couturepro.repositories.UserRepository;

import java.util.HashMap;

@Service
public class AuthentificationService implements IAuthentificationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository utilisateurRepository;
    private final IJwtService jwtService;
    private final UserConverter userConverter;

    public AuthentificationService(AuthenticationManager authenticationManager, UserRepository utilisateurRepository, IJwtService jwtService, UserConverter userConverter) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
        this.jwtService = jwtService;
        this.userConverter = userConverter;
    }

    @Override
    public JwtAuthentificationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getIdentifiant(), signInRequest.getPassword()));
        Utilisateur user = utilisateurRepository.findByTelephone(signInRequest.getIdentifiant()).orElseThrow(
                ()-> new RessourceNotFoundException("User not found",0L)
        );
        if(!user.isAccountNonLocked())
            throw new RuntimeException("Compte verouillé");
        var jwt=jwtService.generateToken(user);
        return new JwtAuthentificationResponse(userConverter.convertToDto(user), jwt);
    }

    public JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userTel=jwtService.extractUsername(refreshTokenRequest.getToken());
        Utilisateur user=utilisateurRepository.findByTelephone(userTel).orElseThrow(()->
                new RessourceNotFoundException("User not found", 000L));
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt=jwtService.generateToken(user);
            return new JwtAuthentificationResponse(userConverter.convertToDto(user), jwt);
        }
        return null;
    }
}
