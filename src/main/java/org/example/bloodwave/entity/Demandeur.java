package org.example.bloodwave.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Demandeur extends Utilisateur {

    @OneToMany
    private List<DemandeSang> demandeSangs;
}