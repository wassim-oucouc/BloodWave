package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.StatutUnite;

import java.util.List;

public interface UniteSangService {

    public UniteSangDtoResponse createUnite(DonDTO dto);

    public UniteSangDtoResponse createUnite(UniteSangDTO dto);

    public UniteSangDtoResponse updateUnite(Long uniteId, UniteSangDTO dto);

    public UniteSangDtoResponse getUniteById(Long uniteId);

    public List<UniteSangDtoResponse> getAllUnites();

    public void deleteUnite(Long uniteId);

    public UniteSangDtoResponse updateStatusUnite(Long uniteId, StatutUnite nouveauStatut);
}
