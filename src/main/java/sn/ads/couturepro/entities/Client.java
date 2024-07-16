package sn.ads.couturepro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    private String civilite;
    private String telephone;
    private String photoCard;
    private String adresse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Utilisateur utilisateur;
}
