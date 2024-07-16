package sn.ads.couturepro.Security.Services;

import org.springframework.security.core.userdetails.UserDetailsService;
import sn.ads.couturepro.entities.Utilisateur;

public interface UserServiceSecurity {

    public UserDetailsService userDetailsService();

    Utilisateur getUserConnect();
}
