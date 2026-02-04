package org.example.bloodwave.dto.response;


import lombok.Data;

@Data
public class InscriptionCollecteDtoResponse {

    private Long id;

    private DonneurDtoResponse donneurDtoResponse;

    private CollecteSangDtoResponse collecteSangDtoResponse;
}
