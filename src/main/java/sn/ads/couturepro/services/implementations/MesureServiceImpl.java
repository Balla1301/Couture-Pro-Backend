package sn.ads.couturepro.services.implementations;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.convertisseurs.MesureConverter;
import sn.ads.couturepro.dtos.Mesure.MesureRequestDto;
import sn.ads.couturepro.dtos.Mesure.MesureResponseDto;
import sn.ads.couturepro.entities.Client;
import sn.ads.couturepro.entities.Mesure;
import sn.ads.couturepro.exceptions.RessourceNotFoundException;
import sn.ads.couturepro.repositories.ClientRepository;
import sn.ads.couturepro.repositories.MesureRepository;
import sn.ads.couturepro.services.MesureService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MesureServiceImpl implements MesureService {

    private final MesureRepository mesureRepository;
    private final ClientRepository clientRepository;
    private final MesureConverter mesureConverter;
    private final ModelMapper modelMapper;

    @Override
    public MesureResponseDto create(Long id, MesureRequestDto requestDto) {
        Client client=clientRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Client",id));
        Mesure mesure=mesureConverter.convertToEntity(requestDto);
        mesure.setClient(client);
        Mesure saved=mesureRepository.save(mesure);
        return mesureConverter.convertToDto(saved);
    }

    @Override
    public MesureResponseDto update(Long id, MesureRequestDto requestDto) {
        Mesure mesure=mesureRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Mesure", id));
        modelMapper.map(requestDto, mesure);
        Mesure updated=mesureRepository.save(mesure);
        return mesureConverter.convertToDto(updated);
    }

    @Override
    public MesureResponseDto find(Long id) {
        Mesure mesure=mesureRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Mesure", id));
        return mesureConverter.convertToDto(mesure);
    }

    @Override
    public List<MesureResponseDto> allByClient(Long id) {
        return mesureRepository.findByClientId(id)
                .stream()
                .map(mesureConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
