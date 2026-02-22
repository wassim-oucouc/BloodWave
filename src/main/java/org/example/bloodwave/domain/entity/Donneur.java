package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Donneur extends Utilisateur {

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    private LocalDate dateDernierDon;
    private Boolean disponible;
    private Integer nombreDonsTotaux;

    @OneToMany
    private List<Don> dons;

    @OneToMany
    @JoinColumn(name = "donneur_id")
    private List<InscriptionCollecte> inscriptions;
}