package com.santt4na.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.santt4na.domain.Anime;

@Service
public class AnimeService {

   private List<Anime> animes = List.of(
         new Anime(1L, "Attack on Titan"),
         new Anime(2L, "Naruto"),
         new Anime(3L, "Dragon Ball Z"));

   public List<Anime> listAll() {
      return animes;
   }

   public Anime findById(long id) {
      return animes.stream()
            .filter(anime -> anime.getId() == id) // Comparação correta para primitivo
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
   }

}
