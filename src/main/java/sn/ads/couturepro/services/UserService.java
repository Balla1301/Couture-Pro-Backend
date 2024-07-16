package sn.ads.couturepro.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto create (UserRequestDto userRequestDto);
    UserResponseDto find (Long id);
    UserResponseDto update (Long id, UserRequestDto userRequestDto);
    List<UserResponseDto> findAll();
    void delete (Long id);
}
