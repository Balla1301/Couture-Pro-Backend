package sn.ads.couturepro.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;
    @OneToOne
    private Mesure mesure;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Utilisateur user;
    private LocalDate livraisonPrevue;
    private Double prix;
    private String etat;
    private String nomModele;
    private String imagesModele;
    private String nomTissu;
    private String imageTissu;
    private String commentaire;

}
