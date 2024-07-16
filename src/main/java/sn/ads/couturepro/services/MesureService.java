package sn.ads.couturepro.services;

import sn.ads.couturepro.dtos.Mesure.MesureRequestDto;
import sn.ads.couturepro.dtos.Mesure.MesureResponseDto;
import sn.ads.couturepro.entities.Mesure;

import java.util.List;

public interface MesureService {
    MesureResponseDto create (Long id, MesureRequestDto requestDto);
    MesureResponseDto update (Long id, MesureRequestDto requestDto);
    MesureResponseDto find(Long id);
    List<MesureResponseDto> allByClient (Long id);
}
