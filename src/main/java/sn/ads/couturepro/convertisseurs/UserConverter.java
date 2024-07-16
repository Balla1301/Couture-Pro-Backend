package sn.ads.couturepro.convertisseurs;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;
import sn.ads.couturepro.entities.Utilisateur;

@Service
@AllArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;
    public UserResponseDto convertToDto(Utilisateur utilisateur){
        UserResponseDto userResponseDto=new UserResponseDto();
        modelMapper.map(utilisateur,userResponseDto);
        return userResponseDto;
    }


    public Utilisateur convertToEntity(UserRequestDto userRequestDtoDto){
        Utilisateur user=new Utilisateur();
        modelMapper.map(userRequestDtoDto,user);
        return user;
    }
}
