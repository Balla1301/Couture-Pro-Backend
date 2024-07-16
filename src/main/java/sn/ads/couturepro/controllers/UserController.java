package sn.ads.couturepro.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ads.couturepro.Security.Dao.JwtAuthentificationResponse;
import sn.ads.couturepro.Security.Dao.SignInRequest;
import sn.ads.couturepro.Security.Interfaces.IAuthentificationService;
import sn.ads.couturepro.dtos.utilisateur.UserRequestDto;
import sn.ads.couturepro.dtos.utilisateur.UserResponseDto;
import sn.ads.couturepro.services.UserService;
import sn.ads.couturepro.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final IAuthentificationService authentificationService;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> create(@Valid @RequestBody UserRequestDto userRequestDto){
        UserResponseDto saved=userService.create(userRequestDto);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(saved, "Inscription réussie.", true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtAuthentificationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authentificationService.signin(signInRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> find (@PathVariable ("id") Long id) {
        UserResponseDto finded=userService.find(id);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(finded, "Utilisateur trouvé.", true);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> findAll() {
        List<UserResponseDto> finded=userService.findAll();
        ApiResponse<List<UserResponseDto>> response = new ApiResponse<>(finded, "Utilisateurs trouvés.", true);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> update(@PathVariable ("id") Long id,@Valid  @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto updated=userService.update(id, userRequestDto);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(updated, "Mises à jour effectuées avec succès.", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/auth/test")
    public ResponseEntity<ApiResponse<String>> tess() {
        ApiResponse<String> response = new ApiResponse<>(null, "test", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
