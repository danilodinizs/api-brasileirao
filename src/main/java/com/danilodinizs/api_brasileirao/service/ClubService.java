package com.danilodinizs.api_brasileirao.service;

import com.danilodinizs.api_brasileirao.dto.ClubRecordDto;
import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.repository.ClubRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public Club registerClub1(Club club) { return clubRepository.save(club); } // maneira utilizando o bean
    public Club registerClub2(ClubRecordDto dto) {
        Club entity = toEntity(dto);
        return clubRepository.save(entity); } // maneira utilizando um método

    private Club toEntity(ClubRecordDto dto) {
        return Club
                .builder()
                .name(dto.name())
                .acronym(dto.acronym())
                .state(dto.state())
                .stadium(dto.stadium())
                .fullName(dto.fullName())
                .build();
    }

    public List<Club> listAllClubs() {
        return clubRepository.findAll();
    }

    public Club findClub(UUID id) {
        return clubRepository.findById(id).get();
    }
}
