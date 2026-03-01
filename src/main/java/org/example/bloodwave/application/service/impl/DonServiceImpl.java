package org.example.bloodwave.application.service.impl;
import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.exceptions.DonNotFoundException;
import org.example.bloodwave.application.exceptions.DonneurNotFoundException;
import org.example.bloodwave.application.mapper.DonMapper;
import org.example.bloodwave.application.service.DonService;
import org.example.bloodwave.application.service.DonneurService;
import org.example.bloodwave.application.service.EmailService;
import org.example.bloodwave.application.service.StockSangService;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.example.bloodwave.domain.repository.DonRepository;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;


@AllArgsConstructor
@Service
public class DonServiceImpl implements DonService {


    private DonRepository donRepository;
    private DonneurRepository donneurRepository;
    private DonMapper donMapper;
    private DonneurService donneurService;
    private StockSangService stockSangService;

    public List<DonDtoResponse> getDonationHistoryById(Long id) {
        Donneur donneur = this.donneurRepository.findById(id).orElseThrow(() -> new DonneurNotFoundException("donneur not found with id" + id));
        return this.donRepository.getDonsByDonneur(donneur).stream().map(donMapper::toDtoResponse).toList();
    }

    public DonDtoResponse createDonation(DonDTO dto) {
      Donneur donneur =   this.donneurRepository.findById(dto.getDonneurId()).orElseThrow(() -> new DonneurNotFoundException("donneur not found with id :" + dto.getDonneurId()));

        boolean eligibility = donneurService.isEligible(donneur.getId());

        if (!eligibility) {
            throw new IllegalStateException(
                    "Donor not eligible: "
            );
        }

        Don don = this.donMapper.toEntity(dto);

        don.setStatut(StatutDon.PLANIFIE);


        Don donCreated = this.donRepository.save(don);

        return this.donMapper.toDtoResponse(donCreated);
    }

    public DonDtoResponse approveDonation(Long id) {
        Don donFound = this.donRepository
                .findById(id)
                .orElseThrow(() -> new DonNotFoundException("don not found with id: " + id));

      Donneur donneur =   this.donneurRepository
              .findById(donFound.getDonneur().getId())
              .orElseThrow(()
                      -> new DonneurNotFoundException("donneur not found with id :" + donFound.getDonneur().getId()));

        donFound.setStatut(StatutDon.CONFIRME);

        donneur
                .setNombreDonsTotaux(donneur.getNombreDonsTotaux() + 1);

        donneur
                .setLastDonationDate(LocalDate
                        .now());

        Don donUpdated = this.donRepository.save(donFound);
        StockSang stockSang = this.stockSangService
                .getStockSangByGroupeSang(donFound.getDonneur().getGroupeSanguin());
        int randomNumber = (int)(Math.random() * 900000) + 100000;

        UniteSang uniteSang = new UniteSang();
        uniteSang.setDon(donFound);
        uniteSang.setStockSang(stockSang);
        uniteSang.setDatePrelevement(LocalDate.now());
        uniteSang.setNumeroUnite("UN-" + randomNumber);
        uniteSang.setDateExpiration(LocalDate.now().plusDays(46));

        this.stockSangService.ajouterAuStock(stockSang,Integer.parseInt(String.valueOf(donFound.getQuantite())),uniteSang);
        return this.donMapper.toDtoResponse(donUpdated);

    }

    public List<DonDtoResponse> getDonsByStatus(StatutDon statutDon)
    {
       return this.donRepository
               .getDonsByStatut(statutDon)
               .stream()
               .map(donMapper::toDtoResponse)
               .toList();
    }

    public Page<DonDtoResponse> getDonsByStatusPageable(int size,int page,StatutDon statutDon)
    {
        Pageable pageable = PageRequest.of(page,size);

      return   this.donRepository.findByStatut(statutDon,pageable).map(donMapper::toDtoResponse);
    }

    public DonDtoResponse cancelDonById(Long donId)
    {
        Don donFound = this.donRepository
                .findById(donId)
                .orElseThrow(() -> new DonNotFoundException("don not found with id: " + donId));

        donFound.setStatut(StatutDon.ANNULE);

        Don donUpdated = this.donRepository.save(donFound);

        return this.donMapper.toDtoResponse(donUpdated);

    }
}
