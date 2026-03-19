package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.application.exceptions.DonNotFoundException;
import org.example.bloodwave.application.exceptions.StockSangNotFoundException;
import org.example.bloodwave.application.exceptions.UnitSangNotFoundException;
import org.example.bloodwave.application.mapper.DonMapper;
import org.example.bloodwave.application.mapper.UnitSangMapper;
import org.example.bloodwave.application.service.UniteSangService;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.StatutUnite;
import org.example.bloodwave.domain.repository.DonRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.example.bloodwave.domain.repository.UnitSangRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class UnitSangServiceImpl implements UniteSangService {

    private final UnitSangRepository unitSangRepository;
    private final DonMapper donMapper;
    private final UnitSangMapper unitSangMapper;
    private final DonRepository donRepository;
    private final StockSangRepository stockSangRepository;

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
    public UniteSangDtoResponse createUnite(UniteSangDTO dto) {
        UniteSang unite = new UniteSang();
        unite.setNumeroUnite(dto.getNumeroUnite() != null ? dto.getNumeroUnite() : "U-" + System.currentTimeMillis());
        unite.setVolume(dto.getVolume());
        unite.setDatePrelevement(dto.getDatePrelevement() != null ? dto.getDatePrelevement() : LocalDate.now());
        unite.setDateExpiration(dto.getDateExpiration());
        unite.setStatut(dto.getStatut() != null ? dto.getStatut() : StatutUnite.DISPONIBLE);

        if (dto.getDonId() != null) {
            Don don = donRepository.findById(dto.getDonId())
                    .orElseThrow(() -> new DonNotFoundException("don not found with id " + dto.getDonId()));
            unite.setDon(don);
            if (unite.getDateExpiration() == null) {
                unite.setDateExpiration(unite.getDatePrelevement().plusDays(46));
            }
            if (unite.getStockSang() == null && don.getHopital() != null && don.getDonneur() != null) {
                StockSang stock = stockSangRepository
                        .findByHopitalAndGroupeSanguin(don.getHopital(), don.getDonneur().getGroupeSanguin())
                        .orElseThrow(() -> new StockSangNotFoundException("stock not found for donation " + don.getId()));
                unite.setStockSang(stock);
            }
        }

        UniteSang created = unitSangRepository.save(unite);
        return unitSangMapper.toDtoResponse(created);
    }

    @Override
    public UniteSangDtoResponse updateUnite(Long uniteId, UniteSangDTO dto) {
        UniteSang unite = unitSangRepository.findById(uniteId)
                .orElseThrow(() -> new UnitSangNotFoundException("unite not found with id " + uniteId));

        if (dto.getNumeroUnite() != null) {
            unite.setNumeroUnite(dto.getNumeroUnite());
        }
        if (dto.getVolume() != null) {
            unite.setVolume(dto.getVolume());
        }
        if (dto.getDatePrelevement() != null) {
            unite.setDatePrelevement(dto.getDatePrelevement());
        }
        if (dto.getDateExpiration() != null) {
            unite.setDateExpiration(dto.getDateExpiration());
        }
        if (dto.getStatut() != null) {
            unite.setStatut(dto.getStatut());
        }
        if (dto.getDonId() != null) {
            Don don = donRepository.findById(dto.getDonId())
                    .orElseThrow(() -> new DonNotFoundException("don not found with id " + dto.getDonId()));
            unite.setDon(don);
        }

        UniteSang updated = unitSangRepository.save(unite);
        return unitSangMapper.toDtoResponse(updated);
    }

    @Override
    public UniteSangDtoResponse getUniteById(Long uniteId) {
        UniteSang unite = unitSangRepository.findById(uniteId)
                .orElseThrow(() -> new UnitSangNotFoundException("unite not found with id " + uniteId));
        return unitSangMapper.toDtoResponse(unite);
    }

    @Override
    public List<UniteSangDtoResponse> getAllUnites() {
        return unitSangRepository.findAll()
                .stream()
                .map(unitSangMapper::toDtoResponse)
                .toList();
    }

    @Override
    public void deleteUnite(Long uniteId) {
        UniteSang unite = unitSangRepository.findById(uniteId)
                .orElseThrow(() -> new UnitSangNotFoundException("unite not found with id " + uniteId));
        unitSangRepository.delete(unite);
    }

    @Override
    public UniteSangDtoResponse updateStatusUnite(Long uniteId, StatutUnite nouveauStatut) {
        UniteSang unite = unitSangRepository.findById(uniteId)
                .orElseThrow(() -> new UnitSangNotFoundException("unite not found with id " + uniteId));

        unite.setStatut(nouveauStatut);
        UniteSang uniteSangCreated =  unitSangRepository.save(unite);

       return this.unitSangMapper.toDtoResponse(uniteSangCreated);


    }
}
