package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Donneur extends Utilisateur {

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    private Boolean disponible;
    private Integer nombreDonsTotaux;
    private Boolean aMaladieChronique;
    private Boolean estSousTraitement;
    private Boolean aSubiChirurgieRecente;
    private Double weight;
    private LocalDate lastDonationDate;
    private Boolean estEnceinte;
    private Boolean aInfectionRecente;

    @Column(name = "get_date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany
    private List<Don> dons;

    @OneToMany
    @JoinColumn(name = "donneur_id")
    private List<InscriptionCollecte> inscriptions;
}