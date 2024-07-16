package sn.ads.couturepro.dtos.utilisateur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ads.couturepro.Validators.ValidCivilite;
import sn.ads.couturepro.Validators.ValidPassword;
import sn.ads.couturepro.Validators.ValidTelephone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotNull(message = "La civilité est obligatoire")
    @ValidCivilite
    private String civilite;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @ValidTelephone
    private String telephone;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @ValidPassword
    private String password;

}
