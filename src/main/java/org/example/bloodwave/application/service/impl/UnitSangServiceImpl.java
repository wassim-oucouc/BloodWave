package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.mapper.UnitSangMapper;
import org.example.bloodwave.application.service.UniteSangService;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.StatutUnite;
import org.example.bloodwave.domain.repository.UnitSangRepository;

import java.time.LocalDate;

public class UnitSangServiceImpl implements UniteSangService {

    private UnitSangRepository unitSangRepository;

    public UniteSang createUnite(Don don)
    {
        UniteSang unite = new UniteSang();
        unite.setDon(don);
        unite.setVolume(450.0);
        unite.setNumeroUnite("U-" + System.currentTimeMillis());
        unite.setDatePrelevement(LocalDate.now());
        unite.setStatut(StatutUnite.DISPONIBLE);
        return this.unitSangRepository.save(unite);
    }
}
