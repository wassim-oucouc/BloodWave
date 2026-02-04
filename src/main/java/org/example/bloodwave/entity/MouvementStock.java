package org.example.bloodwave.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.enumeration.TypeMouvement;
import java.time.LocalDateTime;

@Entity
@Data
public class MouvementStock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeMouvement type;

    private Integer quantite;
    private LocalDateTime date;

    @ManyToOne
    private StockSang stockSang;
}
