package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.StatutUnite;

public interface UniteSangService {

    public UniteSangDtoResponse createUnite(DonDTO dto);
    public UniteSangDtoResponse updateStatusUnite(Long uniteId, StatutUnite nouveauStatut);
}
