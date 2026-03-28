package org.example.bloodwave.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

@Entity
@Data
public class Demandeur extends Utilisateur {

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    @OneToMany
    private List<DemandeSang> demandeSangs;
}