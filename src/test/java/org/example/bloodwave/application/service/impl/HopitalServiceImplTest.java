package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.exceptions.UserNotFoundException;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.application.mapper.HopitalMapper;
import org.example.bloodwave.application.mapper.StockSangMapper;
import org.example.bloodwave.application.service.EmailService;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HopitalServiceImplTest {

    @Mock
    private HopitalMapper hopitalMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private HopitalRepository hopitalRepository;

    @Mock
    private StockSangRepository stockSangRepository;

    @Mock
    private StockSangMapper stockSangMapper;

    @Mock
    private DonneurRepository donneurRepository;

    @Mock
    private DonneurMapper donneurMapper;

    @Mock
    private EmailService emailService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private HopitalServiceImpl hopitalService;

    @Test
    void getCompatibleGroups_shouldReturnAllGroupsForAbPos() {
        Set<GroupeSanguin> result = hopitalService.getCompatibleGroups(GroupeSanguin.AB_POS);

        assertEquals(8, result.size());
        assertEquals(Set.of(
                GroupeSanguin.A_POS, GroupeSanguin.A_NEG,
                GroupeSanguin.B_POS, GroupeSanguin.B_NEG,
                GroupeSanguin.O_POS, GroupeSanguin.O_NEG,
                GroupeSanguin.AB_POS, GroupeSanguin.AB_NEG
        ), result);
    }

    @Test
    void findCompatibleDonneurs_shouldMapRepositoryResults() {
        Donneur donneur = new Donneur();
        DonneurDtoResponse expected = new DonneurDtoResponse();

        when(donneurRepository.findByGroupeSanguinInAndDisponibleTrue(
                hopitalService.getCompatibleGroups(GroupeSanguin.A_POS)))
                .thenReturn(List.of(donneur));
        when(donneurMapper.toDtoResponse(donneur)).thenReturn(expected);

        List<DonneurDtoResponse> result = hopitalService.findCompatibleDonneurs(GroupeSanguin.A_POS);

        assertEquals(1, result.size());
        assertSame(expected, result.get(0));
    }

    @Test
    void sendMessageToUser_shouldSendEmailWhenUserExists() {
        Utilisateur user = new Utilisateur() { };
        user.setId(3L);
        user.setEmail("user@example.com");

        when(utilisateurRepository.findById(3L)).thenReturn(Optional.of(user));

        hopitalService.sendMessageToUser("Subject", "Message", 3L);

        verify(emailService).sendEmail("user@example.com", "Subject", "Message");
    }

    @Test
    void sendMessageToUser_shouldThrowWhenUserDoesNotExist() {
        when(utilisateurRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> hopitalService.sendMessageToUser("Subject", "Message", 99L));
    }
}
