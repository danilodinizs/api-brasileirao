package com.danilodinizs.api_brasileirao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "MATCH")
public class Match implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID matchId;
    Integer goalsHome;
    Integer goalsAway;
    Integer audiencePresent;
    LocalDateTime date;
    Integer round;
    Boolean finished;

    @ManyToOne
    @JoinColumn(name = "homeClub")
    Club homeClub;
    @ManyToOne
    @JoinColumn(name = "awayClub")
    Club awayClub;

}