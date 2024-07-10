package com.danilodinizs.api_brasileirao.dto;

import lombok.Builder;

@Builder
public record FinishedMatchRecordDto(Integer goalsHome, Integer goalsAway, Integer audiencePresent) {
}
