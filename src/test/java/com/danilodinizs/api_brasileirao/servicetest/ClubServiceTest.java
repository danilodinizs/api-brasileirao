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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTest {

    @InjectMocks
    ClubService service;

    @Mock
    ClubRepository clubRepository;

    Club club1, club2;

    List<Club> clubs;

    @BeforeEach
    public void setup() {
        club1 = new Club();
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
        when(clubRepository.findById(club1.getClubId())).thenReturn(club1)
    }
}
