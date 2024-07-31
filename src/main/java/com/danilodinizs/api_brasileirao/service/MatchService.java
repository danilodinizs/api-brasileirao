package com.danilodinizs.api_brasileirao.service;

import com.danilodinizs.api_brasileirao.dto.StandingsClubDto;
import com.danilodinizs.api_brasileirao.dto.StandingsDto;
import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.entity.Match;
import com.danilodinizs.api_brasileirao.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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

            }
            matchDate = matchDate.plusDays(7);
            //Gira os clubs no sentido horário, mantendo o primeiro no lugar
            clubs.add(1, clubs.removeLast());
        }
        matchRepository.saveAll(matches);

       List<Match> matches2 = new ArrayList<>();

        for(Match match : matches) {
            Club clubHome = match.getAwayClub();
            Club clubAway = match.getHomeClub();
            matches2.add(createMatch(match.getDate().plusDays(133), match.getRound() + 19, clubHome, clubAway));
        }

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

    public List<Match> listAllMatches() { return matchRepository.findAll(); }

    public Optional<Match> findMatch(UUID id) { return matchRepository.findById(id); }

    public Optional<Match> finishMatch(UUID id, Match match) {
        Optional<Match> optionalMatch = matchRepository.findById(id);
        if (optionalMatch.isEmpty()) {
           throw new IllegalArgumentException("This match does not exist");
        }
        Match matchvar = optionalMatch.get();
        matchvar.setGoalsAway(match.getGoalsAway());
        matchvar.setGoalsHome(match.getGoalsHome());
        matchvar.setAudiencePresent(match.getAudiencePresent());
        matchvar.setFinished(true);
        return Optional.of(matchRepository.save(matchvar));
    }

    public StandingsDto getStandings() {

        StandingsDto standingsDto = new StandingsDto();
        List<Club> clubs = clubService.listAllClubs();
        clubs.forEach(club -> {
            List<Match> homeMatches = matchRepository.findByHomeClubAndFinished(club, true);
            List<Match> awayMatches = matchRepository.findByAwayClubAndFinished(club, true);

            AtomicReference<Integer> win = new AtomicReference<>(0);
            AtomicReference<Integer> draw = new AtomicReference<>(0);
            AtomicReference<Integer> loses = new AtomicReference<>(0);
            AtomicReference<Integer> golsPro = new AtomicReference<>(0);
            AtomicReference<Integer> golsContra = new AtomicReference<>(0);

            homeMatches.forEach(match -> {
                if (match.getGoalsHome() > match.getGoalsAway()) win.getAndSet(win.get() + 1);
                else if (match.getGoalsHome() < match.getGoalsAway()) loses.getAndSet(loses.get() + 1);
                else draw.getAndSet(draw.get() + 1);
                golsPro.updateAndGet(v -> v + match.getGoalsHome());
                golsContra.updateAndGet(v -> v + match.getGoalsAway());
            });
            awayMatches.forEach(match -> {
                if (match.getGoalsHome() < match.getGoalsAway()) win.getAndSet(win.get() + 1);
                else if (match.getGoalsHome() > match.getGoalsAway()) loses.getAndSet(loses.get() + 1);
                else draw.getAndSet(draw.get() + 1);
                golsPro.updateAndGet(v -> v + match.getGoalsHome());
                golsContra.updateAndGet(v -> v + match.getGoalsAway());
            });

            StandingsClubDto standing =  new StandingsClubDto();
            standing.setClubId(club.getClubId());
            standing.setClub(club.getName());
            standing.setLoses(loses.get());
            standing.setDraws(draw.get());
            standing.setWins(win.get());
            standing.setPoints((win.get() * 3) + draw.get());
            standing.setMatches(win.get() + draw.get() + loses.get());
            standing.setGolsPro(golsPro.get());
            standing.setGolsContra(golsContra.get());

            standingsDto.getStandingClubs().add(standing);
        });

        Collections.sort(standingsDto.getStandingClubs(), Collections.reverseOrder());
        int position = 1;
        for (StandingsClubDto club : standingsDto.getStandingClubs()) {
            club.setPosition(position++);
        }


        return standingsDto;
    }
}
