package com.danilodinizs.api_brasileirao.service;

import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.entity.Match;
import com.danilodinizs.api_brasileirao.repository.ClubRepository;
import com.danilodinizs.api_brasileirao.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    public ClubService clubService;

    @Autowired
    public MatchRepository matchRepository;
    public void createMatches(LocalDateTime firstRound) {
        final List<Club> clubs = clubService.listAllClubs();
        List<Club> allHome = new ArrayList<>();
        List<Club> allAway = new ArrayList<>();
        allHome.addAll(clubs);//.subList(0, clubs.size()/2));
        allAway.addAll(clubs);//.subList(all1.size(), clubs.size()));

        matchRepository.deleteAll();

        List<Match> matches = new ArrayList<>();

        int t = clubs.size();
        int m = clubs.size() / 2;
        LocalDateTime matchDate = firstRound;
        int round = 0;
        for (int i = 0; i < t - 1; i++) {
            round = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Club clubHome;
                Club clubAway;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    clubHome = clubs.get(t - j - 1);
                    clubAway = clubs.get(j);
                } else {
                    clubHome = clubs.get(j);
                    clubAway = clubs.get(t - j - 1);
                }
                if (clubHome == null) {
                    System.out.println("Time  1 null");
                }
                matches.add(createMatch(matchDate, round, clubHome, clubAway));
                matchDate = matchDate.plusDays(7);
            }
            //Gira os clubs no sentido horário, mantendo o primeiro no lugar
            clubs.add(1, clubs.removeLast());
        }

        matches.forEach(System.out::println);

        matchRepository.saveAll(matches);

        List<Match> matches2 = new ArrayList<>();

        matches.forEach(match -> {
            Club clubHome = match.getAwayClub();
            Club clubAway = match.getHomeClub();
            matches2.add(createMatch(match.getDate().plusDays(7L * matches.size()), match.getRound() + matches.size(), clubHome, clubAway));
        });
        matchRepository.saveAll(matches2);
    }
    private Match createMatch(LocalDateTime dataJogo, Integer rodada, Club clubHome, Club clubAway) {
        Match match = new Match();
        match.setHomeClub(clubHome);
        match.setAwayClub(clubAway);
        match.setRound(rodada);
        match.setDate(dataJogo);
        match.setFinished(false);
        match.setGoalsHome(0);
        match.setGoalsAway(0);
        match.setAudiencePresent(0);
        return match;
    }

    public List<Match> listAllMatches() {
        return matchRepository.findAll();
    }
}
