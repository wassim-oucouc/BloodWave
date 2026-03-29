package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.DonneurUpdateDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.exceptions.EmailAlreadyExistsException;
import org.example.bloodwave.application.exceptions.DonneurNotFoundException;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.example.bloodwave.application.service.DonneurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class DonneurServiceImpl implements DonneurService {

    public DonneurMapper donneurMapper;
    public PasswordEncoder passwordEncoder;
    public DonneurRepository donneurRepository;
    public UtilisateurRepository utilisateurRepository;

    public DonneurDtoResponse registerDonneur(DonneurDTO dto) {
        if (dto.getEmail() != null && utilisateurRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new EmailAlreadyExistsException("email already exists: " + dto.getEmail());
        }

        Donneur donneur = this.donneurMapper.toEntity(dto);

        if (dto.getImageProfile() != null) {
            donneur.setImageProfile(dto.getImageProfile());
        }

        String passwordHashed = this.passwordEncoder.encode(donneur.getMotDePasse());

        donneur.setMotDePasse(passwordHashed);
        if (donneur.getNombreDonsTotaux() == null) {
            donneur.setNombreDonsTotaux(0);
        }
        if (donneur.getDisponible() == null) {
            donneur.setDisponible(true);
        }

        Donneur donneurCreated = this.donneurRepository.save(donneur);
        return this.donneurMapper.toDtoResponse(donneurCreated);
    }

    public DonneurDtoResponse updateGroupSanguinById(Long id, GroupeSanguin groupeSanguin) {
        Donneur donneurFound = this.donneurRepository.findById(id)
                .orElseThrow(() -> new DonneurNotFoundException("donneur not found by id : " + id));

        donneurFound.setGroupeSanguin(groupeSanguin);

        Donneur donneurUpdated = this.donneurRepository.save(donneurFound);

        return this.donneurMapper.toDtoResponse(donneurUpdated);
    }

    public DonneurDtoResponse updateDonneurInfo(Long id, DonneurUpdateDTO dto) {
        Donneur donneur = this.donneurRepository.findById(id)
                .orElseThrow(() -> new DonneurNotFoundException("donneur not found by id : " + id));

        if (dto.getPoids() != null)               donneur.setWeight(dto.getPoids());
        if (dto.getGroupeSanguin() != null)       donneur.setGroupeSanguin(dto.getGroupeSanguin());
        if (dto.getDateNaissance() != null)       donneur.setDateOfBirth(dto.getDateNaissance());
        if (dto.getDateDernierDon() != null)      donneur.setLastDonationDate(dto.getDateDernierDon());
        if (dto.getDisponible() != null)          donneur.setDisponible(dto.getDisponible());
        if (dto.getAMaladieChronique() != null)   donneur.setAMaladieChronique(dto.getAMaladieChronique());
        if (dto.getEstSousTraitement() != null)   donneur.setEstSousTraitement(dto.getEstSousTraitement());
        if (dto.getASubiChirurgieRecente() != null) donneur.setASubiChirurgieRecente(dto.getASubiChirurgieRecente());
        if (dto.getEstEnceinte() != null)         donneur.setEstEnceinte(dto.getEstEnceinte());
        if (dto.getAInfectionRecente() != null)   donneur.setAInfectionRecente(dto.getAInfectionRecente());

        return this.donneurMapper.toDtoResponse(this.donneurRepository.save(donneur));
    }

    public DonneurDtoResponse findDonneurById(Long id) {
        Donneur donneur =  this.donneurRepository
                .findById(id)
                .orElseThrow(() -> new DonneurNotFoundException("donneur not found by id : " + id));

       return this.donneurMapper.toDtoResponse(donneur);

    }

    public boolean isEligible(Long id) {
        Donneur donor = this.donneurRepository
                .findById(id)
                .orElseThrow(() -> new DonneurNotFoundException("donner not found with id" + id));

        if (donor.getDateOfBirth() == null) {
            return false;
        }

        int age = Period.between(donor.getDateOfBirth(), LocalDate.now()).getYears();

        if (age < 18 || age > 65)
            return false;

        if (donor.getWeight() == null || donor.getWeight() < 50)
            return false;

        if (donor.getLastDonationDate() != null) {
            System.out.println("LastDonationDate = " + donor.getLastDonationDate());
            System.out.println("Today = " + LocalDate.now());
            System.out.println("is here");
            LocalDate nextEligibleDate = donor.getLastDonationDate().plusWeeks(8);

            if (LocalDate.now().isBefore(nextEligibleDate)) {
                return false;
            }
        }

        if (Boolean.TRUE.equals(donor.getAMaladieChronique()))
            return false;

        if (Boolean.TRUE.equals(donor.getEstSousTraitement()))
            return false;

        if (Boolean.TRUE.equals(donor.getAInfectionRecente()))
            return false;

        if (Boolean.TRUE.equals(donor.getASubiChirurgieRecente()))
            return false;

        if (Boolean.TRUE.equals(donor.getEstEnceinte()))
            return false;

        return true;
    }

    public List<DonneurDtoResponse> getDonneursByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            return List.of();
        }
        return this.donneurRepository.findByVilleIgnoreCase(city.trim())
                .stream()
                .map(donneurMapper::toDtoResponse)
                .toList();
    }

    public Donneur findDonneurEntityById(Long donneurId)
    {
        return this.donneurRepository.findById(donneurId).orElseThrow(() -> new DonneurNotFoundException("Donneur Not found id : " + donneurId));
    }
}
