package sn.ads.couturepro.convertisseurs;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.dtos.client.ClientRequestDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;
import sn.ads.couturepro.entities.Client;
import sn.ads.couturepro.entities.Utilisateur;

@Service
@RequiredArgsConstructor
public class ClientConverter {

    private final ModelMapper modelMapper;
    public ClientResponseDto convertToDto(Client client){
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        modelMapper.map(client,clientResponseDto);
        return clientResponseDto;
    }

    public Client convertToEntity(ClientRequestDto requestDto){
        Client client=new Client();
        modelMapper.map(requestDto,client);
        return client;
    }
}
