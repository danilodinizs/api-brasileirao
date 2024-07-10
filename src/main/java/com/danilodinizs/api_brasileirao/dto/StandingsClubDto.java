package com.danilodinizs.api_brasileirao.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class StandingsClubDto implements Comparable<StandingsClubDto> {
    private String club;
    private UUID clubId;
    private Integer position;
    private Integer points;
    private Integer matches;
    private Integer wins;
    private Integer draws;
    private Integer loses;
    private Integer golsPro;
    private Integer golsContra;
    @Override
    public int compareTo(StandingsClubDto o) {
        return this.getPoints().compareTo(o.getPoints()) ;
    }
}