package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.exceptions.EmailAlreadyExistsException;
import org.example.bloodwave.application.exceptions.HopitalNotFoundException;
import org.example.bloodwave.application.exceptions.UserNotFoundException;
import org.example.bloodwave.application.mapper.DonMapper;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.application.mapper.StockSangMapper;
import org.example.bloodwave.application.service.EmailService;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.application.mapper.HopitalMapper;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.RoleType;
import org.example.bloodwave.domain.repository.DonRepository;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.application.service.HopitalService;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static org.example.bloodwave.domain.enumeration.GroupeSanguin.*;


@Service
@AllArgsConstructor
public class HopitalServiceImpl implements HopitalService {


    public final HopitalMapper hopitalMapper;
    public final PasswordEncoder passwordEncoder;
    public final HopitalRepository hopitalRepository;
    public final StockSangRepository stockSangRepository;
    public final StockSangMapper stockSangMapper;
    public final DonneurRepository donneurRepository;
    public final DonneurMapper donneurMapper;
    public final EmailService emailService;
    public final UtilisateurRepository utilisateurRepository;
    public final DonRepository donRepository;
    public final DonMapper donMapper;



    public HopitalDtoResponse registerHopital(HopitalDTO dto)
    {
        if (dto.getEmail() != null && utilisateurRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new EmailAlreadyExistsException("email already exists: " + dto.getEmail());
        }

        Hopital hopital  = this.hopitalMapper.toEntity(dto);

        if (dto.getImageProfile() != null) {
            hopital.setImageProfile(dto.getImageProfile());
        }

        hopital.setRole(RoleType.HOPITAL);

        String passwordHashed = this.passwordEncoder.encode(hopital.getMotDePasse());

        hopital.setMotDePasse(passwordHashed);
        hopital.setActif(true);

        Hopital hopitalCreated =  this.hopitalRepository.save(hopital);

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

    public Set<GroupeSanguin> getCompatibleGroups(GroupeSanguin groupe)
    {
        return switch (groupe) {

            case O_NEG -> Set.of(O_NEG);

            case O_POS -> Set.of(O_POS, O_NEG);

            case A_NEG -> Set.of(A_NEG, O_NEG);

            case A_POS -> Set.of(A_POS, A_NEG, O_POS, O_NEG);

            case B_NEG -> Set.of(B_NEG, O_NEG);

            case B_POS -> Set.of(B_POS, B_NEG, O_POS, O_NEG);

            case AB_NEG -> Set.of(AB_NEG, A_NEG, B_NEG, O_NEG);

            case AB_POS -> Set.of(
                    A_POS, A_NEG,
                    B_POS, B_NEG,
                    O_POS, O_NEG,
                    AB_POS, AB_NEG
            );
        };
    }

    public List<DonneurDtoResponse> findCompatibleDonneurs(GroupeSanguin groupe) {
      Set<GroupeSanguin> groupeSanguins = this.getCompatibleGroups(groupe);
     return this.donneurRepository
             .findByGroupeSanguinInAndDisponibleTrue(groupeSanguins)
             .stream().map(donneurMapper::toDtoResponse)
             .toList();
    }

    @Override
    public List<DonneurDtoResponse> findCompatibleDonneurs(GroupeSanguin groupe, String ville) {
        List<DonneurDtoResponse> donneursCompatibles = findCompatibleDonneurs(groupe);

        if (ville == null || ville.trim().isEmpty()) {
            return donneursCompatibles;
        }

        String villeNormalized = ville.trim();
        return donneursCompatibles.stream()
                .filter(donneur -> donneur.getVille() != null
                        && donneur.getVille().equalsIgnoreCase(villeNormalized))
                .toList();
    }

    public List<StockSangDtoResponse> getStockForConnectedHospital(Long hopitalId) {

      Hopital hopital =   this.hopitalRepository
                .findById(hopitalId)
                .orElseThrow(() ->
                        new HopitalNotFoundException("hopital not found with id" + hopitalId));

return   stockSangRepository
        .findByHopital(hopital)
        .stream()
        .map(stockSangMapper::toDtoResponse)
        .toList();
    }

    public void sendMessageToUser(String subject,String object,Long userId)
    {
       Utilisateur user =  this.utilisateurRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found with id " + userId));

        this.emailService.sendEmail(user.getEmail(),subject,object);
    }

    @Override
    public List<DonDtoResponse> getDonationsByHopitalId(Long hopitalId) {
        return donRepository.findByHopital_Id(hopitalId)
                .stream()
                .map(donMapper::toDtoResponse)
                .toList();
    }

}
