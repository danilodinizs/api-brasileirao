package com.danilodinizs.api_brasileirao.repository;

import com.danilodinizs.api_brasileirao.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
}
