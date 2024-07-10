package com.danilodinizs.api_brasileirao.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StandingsDto {
    private List<StandingsClubDto> standingClubs = new ArrayList<>();
}
