package org.example.bloodwave.application.dto.request;


import lombok.Data;

@Data
public class InscriptionCollecteDTO {

    private Long id;

    private Long donneurId;

    private boolean joined;


    private Long collectSangId;
}
