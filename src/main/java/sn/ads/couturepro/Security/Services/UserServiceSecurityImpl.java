package sn.ads.couturepro.Security.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.entities.Utilisateur;
import sn.ads.couturepro.exceptions.RessourceNotFoundException;
import sn.ads.couturepro.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceSecurityImpl implements UserServiceSecurity {

    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByTelephone(username).orElseThrow(
                        ()-> new RessourceNotFoundException("User not Found", 00L)
                );
            }
        };
    }

    @Override
    public Utilisateur getUserConnect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Utilisateur userConnect = userRepository.findByTelephone(currentPrincipalName).orElseThrow(()->
                new RessourceNotFoundException("User not found", null));
        return userConnect;

    }

}
