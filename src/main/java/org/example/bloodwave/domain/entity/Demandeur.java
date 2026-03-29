package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

@Entity
@Data
public class Demandeur extends Utilisateur {

    @Column(name = "groupe_sanguin")
    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    @OneToMany
    private List<DemandeSang> demandeSangs;
}