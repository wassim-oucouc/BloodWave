package org.example.bloodwave.application.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.example.bloodwave.domain.entity.MouvementStock;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;
@Data
public class StockSangDTO {

    private Long id;
    private GroupeSanguin groupeSanguin;
    private Integer quantiteDisponible;
    private Integer seuilAlerte;
    private Long hopitalId;
}
