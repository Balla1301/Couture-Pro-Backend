package sn.ads.couturepro.services.implementations;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.Security.Services.UserServiceSecurity;
import sn.ads.couturepro.convertisseurs.ClientConverter;
import sn.ads.couturepro.dtos.client.ClientRequestDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;
import sn.ads.couturepro.entities.Client;
import sn.ads.couturepro.entities.Utilisateur;
import sn.ads.couturepro.exceptions.BadRequestException;
import sn.ads.couturepro.exceptions.NotAuthenticatedException;
import sn.ads.couturepro.exceptions.RessourceNotFoundException;
import sn.ads.couturepro.repositories.ClientRepository;
import sn.ads.couturepro.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserServiceSecurity userServiceSecurity;
    private final ClientConverter clientConverter;
    private final ModelMapper modelMapper;
    public ClientResponseDto create(ClientRequestDto requestDto) throws BadRequestException, NotAuthenticatedException {
        Utilisateur userConnect = userServiceSecurity.getUserConnect();
        if (userConnect == null)
            throw new NotAuthenticatedException("Non Authentifié");
        if (clientRepository.existsByTelephoneAndUtilisateurId(requestDto.getTelephone(), userConnect.getId()))
            throw new BadRequestException("Un client avec ce numéro de téléphone existe déjà.");
        Client client = clientConverter.convertToEntity(requestDto);
        client.setUtilisateur(userConnect);

        Client saved = clientRepository.save(client);
        return clientConverter.convertToDto(saved);
    }

    @Override
    public ClientResponseDto find(Long id) {
        return clientRepository.findById(id)
                .map(clientConverter::convertToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Client", id));
    }

    @Override
    public ClientResponseDto update(Long id, ClientRequestDto requestDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Client", id));
        if (!client.getTelephone().equals(requestDto.getTelephone())) {
            if (clientRepository.existsByTelephoneAndIdNot(requestDto.getTelephone(), id)) {
                throw new sn.ads.couturepro.exceptions.BadRequestException("Un client avec cet téléphone existe déjà.");
            }
        }
        modelMapper.map(requestDto, client);
        Client saved = clientRepository.save(client);
        return clientConverter.convertToDto(saved);
    }

    public List<ClientResponseDto> all() {
        Utilisateur userConnect = userServiceSecurity.getUserConnect();

        if (userConnect == null)
            throw new NotAuthenticatedException("Non Authentifié");

        return clientRepository.findByUtilisateurId(userConnect.getId())
                .stream()
                .map(clientConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
