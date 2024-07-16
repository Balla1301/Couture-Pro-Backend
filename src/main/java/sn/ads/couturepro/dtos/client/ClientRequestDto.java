package sn.ads.couturepro.dtos.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ads.couturepro.Validators.ValidTelephone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "La civilité est obligatoire")
    @Pattern(regexp = "^(M|Mme)$", message = "La civilité doit être 'M' ou 'Mme'")
    private String civilite;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @ValidTelephone
    private String telephone;

    private String photoCard;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
}
