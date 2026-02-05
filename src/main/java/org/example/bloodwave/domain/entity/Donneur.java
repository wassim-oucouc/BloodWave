package org.example.bloodwave.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
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

    private List<Don> dons;

    @OneToMany(mappedBy = "donneur")
    private List<InscriptionCollecte> inscriptions;
}