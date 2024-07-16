package sn.ads.couturepro.dtos.commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ads.couturepro.dtos.Mesure.MesureResponseDto;
import sn.ads.couturepro.dtos.client.ClientResponseDto;
import sn.ads.couturepro.entities.Client;
import sn.ads.couturepro.entities.Mesure;
import sn.ads.couturepro.entities.Utilisateur;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandeRepsonseDto {

    private Long id;
    private ClientResponseDto client;
    private MesureResponseDto mesure;
    private LocalDate livraisonPrevue;
    private Double prix;
    private String etat;
    private String nomModele;
    private String imagesModele;
    private String nomTissu;
    private String imageTissu;
    private String commentaire;
}