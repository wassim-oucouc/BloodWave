package org.example.bloodwave.application.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:45+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CollecteSangMapperImpl extends CollecteSangMapper {

    @Override
    public CollecteSangDtoResponse toDtoResponse(CollecteSang collecteSang) {
        if ( collecteSang == null ) {
            return null;
        }

        CollecteSangDtoResponse collecteSangDtoResponse = new CollecteSangDtoResponse();

        collecteSangDtoResponse.setId( collecteSang.getId() );
        collecteSangDtoResponse.setDateCollecte( collecteSang.getDateCollecte() );
        collecteSangDtoResponse.setLieu( collecteSang.getLieu() );
        collecteSangDtoResponse.setCapaciteMax( collecteSang.getCapaciteMax() );
        collecteSangDtoResponse.setStatut( collecteSang.getStatut() );

        return collecteSangDtoResponse;
    }

    @Override
    public CollecteSang toEntity(CollecteSangDTO collecteSangDTO) {
        if ( collecteSangDTO == null ) {
            return null;
        }

        CollecteSang collecteSang = new CollecteSang();

        collecteSang.setId( collecteSangDTO.getId() );
        collecteSang.setDateCollecte( collecteSangDTO.getDateCollecte() );
        collecteSang.setLieu( collecteSangDTO.getLieu() );
        collecteSang.setCapaciteMax( collecteSangDTO.getCapaciteMax() );
        collecteSang.setStatut( collecteSangDTO.getStatut() );
        List<InscriptionCollecte> list = collecteSangDTO.getInscriptions();
        if ( list != null ) {
            collecteSang.setInscriptions( new ArrayList<InscriptionCollecte>( list ) );
        }

        return collecteSang;
    }
}
