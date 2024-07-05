package com.danilodinizs.api_brasileirao.service;

import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;
    public void registerClub(Club club) {
        clubRepository.save(club);
    }

    public List<Club> listAllClubs() {
        return clubRepository.findAll();
    }

    public Club findClub(UUID id) {
        return clubRepository.findById(id).get();
    }
}
