package com.danilodinizs.api_brasileirao.rest;

import com.danilodinizs.api_brasileirao.dto.ClubRecordDto;
import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.service.ClubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClubRestController {

    @Autowired
    private ClubService clubService;

    @PostMapping("/clubs")
    public ResponseEntity<Club> saveClub1(@RequestBody ClubRecordDto clubRecordDto) {  // maneira utilizando bean
        Club club = new Club();
        BeanUtils.copyProperties(clubRecordDto, club);
        return ResponseEntity.ok().body(clubService.registerClub1(club));
    }

//    @PostMapping("/clubs")
//    public ResponseEntity<Club> saveClub2(@RequestBody ClubRecordDto dto) { // maneira utilizando método
//        return ResponseEntity.ok().body(clubService.registerClub2(dto));
//    }

    @GetMapping("/clubs")
    public ResponseEntity<List<ClubRecordDto>> getClubs() {
        return ResponseEntity.status(HttpStatus.OK).body(clubService.listAllClubs());
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<Object> getClub(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(clubService.findClub(id));
    }
}
