package sn.ads.couturepro.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ads.couturepro.dtos.Mesure.MesureRequestDto;
import sn.ads.couturepro.dtos.Mesure.MesureResponseDto;
import sn.ads.couturepro.dtos.client.ClientRequestDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;
import sn.ads.couturepro.services.MesureService;
import sn.ads.couturepro.utils.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mesures")
public class MesureController {

    private final MesureService mesureService;

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<MesureResponseDto>> create(@PathVariable("id") Long id, @Valid @RequestBody MesureRequestDto requestDto) throws BadRequestException {
        MesureResponseDto saved=mesureService.create(id,requestDto);
        ApiResponse<MesureResponseDto> response = new ApiResponse<>(saved, "Mesure ajoutée avec succés.", true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MesureResponseDto>> find(@PathVariable("id") Long id) {
        MesureResponseDto finded=mesureService.find(id);
        ApiResponse<MesureResponseDto> response = new ApiResponse<>(finded, "Mesure trouvé.", true);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MesureResponseDto>> update (@PathVariable ("id") Long id,@Valid @RequestBody MesureRequestDto requestDto) {
        MesureResponseDto updated=mesureService.update(id, requestDto);
        ApiResponse<MesureResponseDto> response = new ApiResponse<>(updated, "Mises à jour effectuées avec succès.", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ApiResponse<List<MesureResponseDto>>> findAllByClient(@PathVariable ("id") Long id) {
        List<MesureResponseDto> finded=mesureService.allByClient(id);
        ApiResponse<List<MesureResponseDto>> response = new ApiResponse<>(finded, "Mesures trouvées trouvés.", true);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
