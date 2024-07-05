package com.danilodinizs.api_brasileirao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "MATCH")
public class Match implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID uuid;
    Club homeClub;
    Club awayClub;
    Integer goalsHClub;
    Integer goalsAClub;

}