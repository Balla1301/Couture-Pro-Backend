package sn.ads.couturepro.services;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import sn.ads.couturepro.dtos.client.ClientRequestDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;

import java.util.List;
public interface ClientService {

    ClientResponseDto create(ClientRequestDto requestDto) throws BadRequestException;
    ClientResponseDto find(Long id);
    ClientResponseDto update(Long id, ClientRequestDto requestDto);
    List<ClientResponseDto> all();
}
