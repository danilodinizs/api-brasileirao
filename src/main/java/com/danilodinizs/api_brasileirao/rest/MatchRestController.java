package com.danilodinizs.api_brasileirao.rest;

import com.danilodinizs.api_brasileirao.dto.FinishedMatchRecordDto;
import com.danilodinizs.api_brasileirao.dto.MatchRecordDto;
import com.danilodinizs.api_brasileirao.entity.Match;
import com.danilodinizs.api_brasileirao.service.MatchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MatchRestController {

    @Autowired
    public MatchService matchService;
    @PostMapping("/matches/create-matches")
    public ResponseEntity<Void> createMatches() {
        matchService.createMatches(LocalDateTime.of(2024, 4, 13, 16,0));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getMatches() {
        List<Match> listMatches = matchService.listAllMatches();
        return ResponseEntity.ok().body(listMatches);
    }

    @PostMapping("/finish/{id}")
    public ResponseEntity<Optional<Match>> finishMatch(@PathVariable (value = "id") UUID id, @RequestBody FinishedMatchRecordDto finishedMatchRecordDto) {
        Match match = new Match();
        BeanUtils.copyProperties(finishedMatchRecordDto, match);
        return ResponseEntity.ok().body(matchService.finishMatch(id, match));
    }

    @GetMapping("/matches/{id}")
    public ResponseEntity<Optional<Match>> findMatch(@PathVariable (value = "id") UUID id) {
        Optional<Match> match = matchService.findMatch(id);
        if(match.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok().body(match);
    }

}