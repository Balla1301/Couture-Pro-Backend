package sn.ads.couturepro.services.implementations;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.convertisseurs.UserConverter;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;
import sn.ads.couturepro.entities.Utilisateur;
import sn.ads.couturepro.exceptions.BadRequestException;
import sn.ads.couturepro.exceptions.RessourceNotFoundException;
import sn.ads.couturepro.repositories.UserRepository;
import sn.ads.couturepro.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        if(userRepository.existsByTelephone(userRequestDto.getTelephone()))
            throw new BadRequestException("Utilisateur avec ce téléphone existe déjà.");
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        Utilisateur user=userConverter.convertToEntity(userRequestDto);
        Utilisateur saved = userRepository.save(user);
        return userConverter.convertToDto(saved);
    }

    @Override
    public UserResponseDto find(Long id) {
        return userRepository.findById(id)
                .map(userConverter::convertToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur", id));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        Utilisateur user = userRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur", id));
        if (!user.getTelephone().equals(userRequestDto.getTelephone())) {
            if (userRepository.existsByTelephoneAndIdNot(userRequestDto.getTelephone(), id)) {
                throw new BadRequestException("Un utilisateur avec cet email existe déjà.");
            }
        }
        modelMapper.map(userRequestDto, user);
        Utilisateur saved = userRepository.save(user);
        return userConverter.convertToDto(saved);
    }

    @Override
    public List<UserResponseDto> findAll() {
            return userRepository.findAll()
                    .stream()
                    .map(userConverter::convertToDto)
                    .collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {

    }


}
