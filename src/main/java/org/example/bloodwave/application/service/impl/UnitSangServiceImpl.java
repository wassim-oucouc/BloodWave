package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.application.mapper.DonMapper;
import org.example.bloodwave.application.mapper.UnitSangMapper;
import org.example.bloodwave.application.service.UniteSangService;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.StatutUnite;
import org.example.bloodwave.domain.repository.UnitSangRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UnitSangServiceImpl implements UniteSangService {

    private UnitSangRepository unitSangRepository;
    private DonMapper donMapper;
    private UnitSangMapper unitSangMapper;

    public UniteSangDtoResponse createUnite(DonDTO dto) {

        Don don = this.donMapper.toEntity(dto);
        UniteSang unite = new UniteSang();
        unite.setDon(don);
        unite.setVolume(450.0);
        unite.setNumeroUnite("U-" + System.currentTimeMillis());
        unite.setDatePrelevement(LocalDate.now());
        unite.setStatut(StatutUnite.DISPONIBLE);
        UniteSang uniteSang = this.unitSangRepository.save(unite);

       return this.unitSangMapper.toDtoResponse(uniteSang);

    }

    @Override
    public UniteSangDtoResponse updateStatusUnite(Long uniteId, StatutUnite nouveauStatut) {
        UniteSang unite = unitSangRepository.findById(uniteId)
                .orElseThrow(() -> new RuntimeException("Unité de sang introuvable avec l'ID : " + uniteId));

        unite.setStatut(nouveauStatut);
        UniteSang uniteSangCreated =  unitSangRepository.save(unite);

       return this.unitSangMapper.toDtoResponse(uniteSangCreated);


    }
}
