package sn.ads.couturepro.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ads.couturepro.dtos.client.ClientRequestDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;
import sn.ads.couturepro.services.ClientService;
import sn.ads.couturepro.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDto>> create(@Valid @RequestBody ClientRequestDto requestDto) throws BadRequestException {
        ClientResponseDto saved=clientService.create(requestDto);
        ApiResponse<ClientResponseDto> response = new ApiResponse<>(saved, "Client ajouté avec succés.", true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> find(@PathVariable("id") Long id) {
        ClientResponseDto finded=clientService.find(id);
        ApiResponse<ClientResponseDto> response = new ApiResponse<>(finded, "Client trouvé.", true);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> update (@PathVariable ("id") Long id,@Valid @RequestBody ClientRequestDto requestDto) {
        ClientResponseDto updated=clientService.update(id, requestDto);
        ApiResponse<ClientResponseDto> response = new ApiResponse<>(updated, "Mises à jour effectuées avec succès.", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> findAll() {
        List<ClientResponseDto> finded=clientService.all();
        ApiResponse<List<ClientResponseDto>> response = new ApiResponse<>(finded, "Clients trouvés.", true);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
