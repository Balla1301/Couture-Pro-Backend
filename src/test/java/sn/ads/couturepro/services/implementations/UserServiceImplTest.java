package sn.ads.couturepro.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.ads.couturepro.convertisseurs.UserConverter;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;
import sn.ads.couturepro.entities.Utilisateur;
import sn.ads.couturepro.exceptions.BadRequestException;
import sn.ads.couturepro.exceptions.RessourceNotFoundException;
import sn.ads.couturepro.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;
    @InjectMocks
    private UserServiceImpl userService;
    private UserRequestDto userRequestDto;
    private Utilisateur utilisateur;
    private UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setTelephone("771234567");

        utilisateur = new Utilisateur();
        utilisateur.setId(1L);

        userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
    }

    @Test
    void createUser_Success() {
        when(userRepository.existsByTelephone(userRequestDto.getTelephone())).thenReturn(false);
        when(userConverter.convertToEntity(userRequestDto)).thenReturn(utilisateur);
        when(userRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);
        when(userConverter.convertToDto(utilisateur)).thenReturn(userResponseDto);

        UserResponseDto result = userService.create(userRequestDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository).save(any(Utilisateur.class));
    }

    @Test
    void createUser_ExistingTelephone_ThrowsBadRequestException() {
        when(userRepository.existsByTelephone(userRequestDto.getTelephone())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> userService.create(userRequestDto));
        verify(userRepository, never()).save(any(Utilisateur.class));
    }

    @Test
    void findUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(userConverter.convertToDto(utilisateur)).thenReturn(userResponseDto);

        UserResponseDto result = userService.find(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findUser_NotFound_ThrowsRessourceNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RessourceNotFoundException.class, () -> userService.find(1L));
    }
}