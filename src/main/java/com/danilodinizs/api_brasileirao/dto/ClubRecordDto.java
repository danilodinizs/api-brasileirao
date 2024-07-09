package com.danilodinizs.api_brasileirao.dto;

import lombok.Builder;
import lombok.Data;

@Builder
public record ClubRecordDto(String name, String state, String fullName, String acronym, String stadium) {

}
