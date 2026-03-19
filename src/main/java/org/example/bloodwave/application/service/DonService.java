package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonService {

    public DonDtoResponse getDonById(Long id);

    public List<DonDtoResponse> getDonationHistoryById(Long id);

    public DonDtoResponse createDonation(DonDTO dto);

    public DonDtoResponse approveDonation(Long id);

    public List<DonDtoResponse> getDonsByStatus(StatutDon statutDon);

    public Page<DonDtoResponse> getDonsByStatusPageable(int size, int page, StatutDon statutDon);

    public DonDtoResponse cancelDonById(Long donId);
}
