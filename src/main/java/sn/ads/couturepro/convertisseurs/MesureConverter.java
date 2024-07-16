package sn.ads.couturepro.convertisseurs;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.dtos.Mesure.MesureRequestDto;
import sn.ads.couturepro.dtos.Mesure.MesureResponseDto;
import sn.ads.couturepro.dtos.client.ClientRequestDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;
import sn.ads.couturepro.entities.Client;
import sn.ads.couturepro.entities.Mesure;

@Service
@RequiredArgsConstructor
public class MesureConverter {

    private final ModelMapper modelMapper;
    public MesureResponseDto convertToDto(Mesure mesure){
        MesureResponseDto mesureResponseDto = new MesureResponseDto();
        modelMapper.map(mesure,mesureResponseDto);
        return mesureResponseDto;
    }

    public Mesure convertToEntity(MesureRequestDto requestDto){
        Mesure mesure=new Mesure();
        modelMapper.map(requestDto,mesure);
        return mesure;
    }
}
