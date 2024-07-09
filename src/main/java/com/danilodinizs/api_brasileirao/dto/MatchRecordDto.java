package com.danilodinizs.api_brasileirao.dto;

import com.danilodinizs.api_brasileirao.entity.Club;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record MatchRecordDto(Integer goalsHome,
                             Integer goalsAway,
                             Integer audiencePresent,
                             LocalDateTime date, Integer round,
                             Boolean finished,
                             ClubRecordDto homeClub,
                             ClubRecordDto awayClub) {

}
