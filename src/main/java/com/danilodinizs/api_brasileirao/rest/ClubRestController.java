package com.danilodinizs.api_brasileirao.rest;

import com.danilodinizs.api_brasileirao.dto.ClubRecordDto;
import com.danilodinizs.api_brasileirao.entity.Club;
import com.danilodinizs.api_brasileirao.service.ClubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/clubs")
public class ClubRestController {

    @Autowired
    private ClubService clubService;

    @PostMapping
    public ResponseEntity<Club> saveClub(@PathVariable ClubRecordDto clubRecordDto) {
        var club = new Club();
        BeanUtils.copyProperties(clubRecordDto, club);
        return ResponseEntity.ok().body(clubService.registerClub(club));
    }
    @GetMapping
    public ResponseEntity<List<Club>> getClubs() {
        return ResponseEntity.ok().body(clubService.listAllClubs());
    }

    @ApiOperation(value = "Club data")
    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getClub(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(clubService.findClub(id));
    }
}
