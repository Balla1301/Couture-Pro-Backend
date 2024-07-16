package sn.ads.couturepro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mesure {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;
}
