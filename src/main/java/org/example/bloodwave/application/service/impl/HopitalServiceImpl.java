package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.application.mapper.HopitalMapper;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.application.service.HopitalService;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class HopitalServiceImpl implements HopitalService {


    public HopitalMapper hopitalMapper;
    public PasswordEncoder passwordEncoder;
    public HopitalRepository hopitalAuthRepository;
    public StockSangRepository stockSangRepository;



    public HopitalDtoResponse registerHopital(HopitalDTO dto)
    {
        Hopital hopital  = this.hopitalMapper.toEntity(dto);

        String passwordHashed = this.passwordEncoder.encode(hopital.getMotDePasse());

        hopital.setMotDePasse(passwordHashed);

        Hopital hopitalCreated =  this.hopitalAuthRepository.save(hopital);

        for (GroupeSanguin gs : GroupeSanguin.values()) {
            StockSang stock = new StockSang();
            stock.setGroupeSanguin(gs);
            stock.setQuantiteDisponible(0);
            stock.setSeuilAlerte(5);
            stock.setHopital(hopital);
            stockSangRepository.save(stock);
        }

        return this.hopitalMapper.toDtoResponse(hopitalCreated);
    }
}
