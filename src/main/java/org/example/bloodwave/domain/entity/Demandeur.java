package org.example.bloodwave.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Demandeur extends Utilisateur {

    @OneToMany
    private List<DemandeSang> demandeSangs;
}