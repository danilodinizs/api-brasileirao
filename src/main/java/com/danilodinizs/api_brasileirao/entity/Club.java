package com.danilodinizs.api_brasileirao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CLUB")
public class Club implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID clubId;
    @Column(length = 20)
    String name;
    @Column(length = 3, name = "short name")
    String acronym;
    @Column(length = 2)
    String state;
    @Column(length = 35)
    String stadium;



}
