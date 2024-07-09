package com.danilodinizs.api_brasileirao.rest;

import com.danilodinizs.api_brasileirao.entity.Match;
import com.danilodinizs.api_brasileirao.service.MatchService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MatchRestController {

    @Autowired
    public MatchService matchService;
    @PostMapping("/matches/create-matches")
    public ResponseEntity<Void> createMatches() {
        matchService.createMatches(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getMatches() {
        List<Match> listMatches = matchService.listAllMatches();
        return ResponseEntity.ok().body(listMatches);
    }
}
