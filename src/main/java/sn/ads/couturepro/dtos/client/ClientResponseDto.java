package sn.ads.couturepro.dtos.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
    private Long id;
    private String prenom;
    private String nom;
    private String civilite;
    private String telephone;
    private String photoCard;
    private String adresse;
}
