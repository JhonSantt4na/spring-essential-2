package com.santt4na.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santt4na.domain.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
