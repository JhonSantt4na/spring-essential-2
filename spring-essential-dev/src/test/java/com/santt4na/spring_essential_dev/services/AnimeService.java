package com.santt4na.spring_essential_dev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santt4na.spring_essential_dev.domain.Anime;

@Service
public class AnimeService {
   public List<Anime> listAll() {
      return List.of(
            new Anime(1L, "Attack on Titan"),
            new Anime(2L, "Naruto"),
            new Anime(3L, "Dragon Ball Z"));
   }
}
