package com.danilodinizs.api_brasileirao.servicetest;


import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.service.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.danilodinizs.api_brasileirao.entity.Match;
import com.danilodinizs.api_brasileirao.repository.MatchRepository;
import com.danilodinizs.api_brasileirao.service.MatchService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    @InjectMocks
    MatchService matchService;
    @Mock
    private ClubService clubService;

    @Mock
    MatchRepository matchRepository;

    Match match1, match2;
    Club club1, club2;
    UUID clubId;
    LocalDateTime ldc;

    List<Match> matches;
    List<Club> clubs;

    @BeforeEach
    public void setup() {
        match1 = new Match();
        match1.setHomeClub(match1.getHomeClub());
        match1.setAwayClub(match1.getAwayClub());
        match1.setRound(1);
        match1.setDate(match1.getDate());
        match1.setFinished(false);
        match1.setGoalsHome(4);
        match1.setGoalsAway(0);
        match1.setAudiencePresent(65000);

        match2 = new Match();
        match2.setHomeClub(match2.getHomeClub());
        match2.setAwayClub(match2.getAwayClub());
        match2.setRound(1);
        match2.setDate(match1.getDate());
        match2.setFinished(false);
        match2.setGoalsHome(1);
        match2.setGoalsAway(3);
        match2.setAudiencePresent(42000);

        matches = Arrays.asList(match1, match2);

        clubs = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Club club = new Club();
            club.setClubId(UUID.randomUUID());
            club.setName("Club " + i);
            clubs.add(club);
        }

        ldc = LocalDateTime.now();
    }

    @Test
    void mustListAllMatchesSuccessfully() {
        when(matchRepository.findAll()).thenReturn(matches);
        List<Match> result = matchService.listAllMatches();
        assertEquals(matches, result);
    }

    @Test
     void testCreateMatches() {
        when(clubService.listAllClubs()).thenReturn(clubs);

        matchService.createMatches(ldc);

        verify(matchRepository, times(2)).saveAll(anyList());
    }

}
