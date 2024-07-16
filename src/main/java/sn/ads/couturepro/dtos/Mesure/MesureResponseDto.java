package sn.ads.couturepro.dtos.Mesure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesureResponseDto {

    private Long id;
    private String intitulé;
    private String description;
    private double tourDeCou;
    private double tourDePoitrine;
    private double tourDeTaille;
    private double tourDeHanches;
    private double longueurEpaule;
    private double longueurDeBras;
    private double tourDeBiceps;
    private double tourDePoignet;
    private double EntreJambe;
    private double longueurDeJambe;
    private double tourDeCuisse;
    private double tourDeGenou;
    private double tourDemollet;
}
