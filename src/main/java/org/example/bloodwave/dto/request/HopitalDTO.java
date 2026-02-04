package org.example.bloodwave.dto.request;



import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HopitalDTO extends UtilisateurDTO {

    private String nomHopital;
    private String ville;
    private Integer capaciteStockage;

    @Transient
    private List<Long> stockIds;

    @Transient
    private List<Long> donsRecuIds;

    @Transient
    private List<Long> collectIds;
}
