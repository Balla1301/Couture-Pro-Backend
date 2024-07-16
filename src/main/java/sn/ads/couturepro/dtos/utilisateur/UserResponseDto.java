package sn.ads.couturepro.dtos.utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {

    private Long id;
    private String nom;
    private String prenom;
    private String civilite;
    private String adresse;
    private String telephone;


}
