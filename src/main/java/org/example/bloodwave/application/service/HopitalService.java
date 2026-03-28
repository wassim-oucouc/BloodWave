package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface HopitalService {

    public HopitalDtoResponse registerHopital(HopitalDTO dto);
    public Set<GroupeSanguin> getCompatibleGroups(GroupeSanguin groupe);
    public List<DonneurDtoResponse> findCompatibleDonneurs(GroupeSanguin groupe);
    public List<DonneurDtoResponse> findCompatibleDonneurs(GroupeSanguin groupe, String ville);
    public List<StockSangDtoResponse> getStockForConnectedHospital(Long hopitalId);
    public void sendMessageToUser(String subject,String object,Long userId);
    public List<DonDtoResponse> getDonationsByHopitalId(Long hopitalId);

}
