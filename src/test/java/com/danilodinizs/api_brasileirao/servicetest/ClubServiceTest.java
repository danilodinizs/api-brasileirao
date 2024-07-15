package com.danilodinizs.api_brasileirao.servicetest;

import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.repository.ClubRepository;
import com.danilodinizs.api_brasileirao.service.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

import java.util.*;

import static jakarta.persistence.GenerationType.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTest {

    @InjectMocks
    ClubService service;

    @Mock
    ClubRepository clubRepository;

    Club club1, club2;

    List<Club> clubs;

    UUID clubId = java.util.UUID.randomUUID();

    @BeforeEach
    public void setup() {
        club1 = new Club();
        club1.setClubId(clubId);
        club1.setName("Flamengo");
        club1.setAcronym("FLA");
        club1.setState("RJ");
        club1.setStadium("Maracanã");
        club1.setFullName("CRF");

        club2 = new Club();
        club2.setName("Palmeiras");
        club2.setAcronym("PAL");
        club2.setState("SP");
        club2.setStadium("Allianz Parque");
        club2.setFullName("SEP");

        clubs = Arrays.asList(club1, club2);
    }

    @Test
    void mustListAllClubsSuccessfully() {
        when(clubRepository.findAll()).thenReturn(clubs);
        List<Club> result = service.listAllClubs();
        assertEquals(clubs, result);
    }

    @Test
    void mustReturnOneClubByIdSuccessfully() {
        when(clubRepository.findById(club1.getClubId())).thenReturn(Optional.of(club1));
        Optional<Club> result = service.findClub(club1.getClubId());
        assertEquals(club1, result.get());
    }

    @Test
    void mustUpdateClubSuccessfully() {
        when(clubRepository.save(any(Club.class))).thenReturn(club1);
        Club result = service.updateClub(club1);
        assertEquals(club1, result);
    }

    @Test
    void mustRegisterClubSuccessfully() {
        when(clubRepository.save(any(Club.class))).thenReturn(club1);
        Club result = service.registerClub1(club1);
        assertEquals(club1, result);
    }
}
