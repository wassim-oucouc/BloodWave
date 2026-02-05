package org.example.bloodwave.application.dto.response;


import lombok.Data;

@Data
public class InscriptionCollecteDtoResponse {

    private Long id;

    private DonneurDtoResponse donneurDtoResponse;

    private CollecteSangDtoResponse collecteSangDtoResponse;
}
