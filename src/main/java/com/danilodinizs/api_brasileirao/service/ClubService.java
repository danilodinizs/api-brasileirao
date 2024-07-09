package com.danilodinizs.api_brasileirao.service;

import com.danilodinizs.api_brasileirao.dto.ClubRecordDto;
import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public Club registerClub1(Club club) { return clubRepository.save(club); } // maneira utilizando o bean
    public Club registerClub2(ClubRecordDto dto) { return clubRepository.save(toEntity(dto)); } // maneira utilizando um método

    private Club toEntity(ClubRecordDto dto) {
        Club club = new Club();
        club.setName(dto.name());
        club.setName(dto.acronym());
        club.setState(dto.state());
        club.setStadium(dto.stadium());
        club.setFullName(dto.fullName());
        return club;
    }

    public List<Club> listAllClubs() {
        // return clubRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
        return clubRepository.findAll();
    }

    private ClubRecordDto toDto(Club club) {
        return ClubRecordDto
                .builder()
                .name(club.getName())
                .acronym(club.getAcronym())
                .state(club.getState())
                .stadium(club.getStadium())
                .fullName(club.getFullName())
                .build();
    }

    public Optional<Club> findClub(UUID id) {
        return clubRepository.findById(id);
    }

    public Club updateClub(Club club) {
        return clubRepository.save(club);
    }
}
