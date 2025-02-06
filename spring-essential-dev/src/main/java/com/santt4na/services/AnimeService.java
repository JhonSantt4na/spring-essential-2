package com.santt4na.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.santt4na.domain.Anime;

@Service
public class AnimeService {

   private static List<Anime> animes;

   static { // Para podermos adicionar elementos na lista
      animes = new ArrayList<>(List.of(
            new Anime(1L, "Attack on Titan"),
            new Anime(2L, "Naruto")));
   }

   public List<Anime> listAll() {
      return animes;
   }

   public Anime findById(long id) {
      return animes.stream()
            .filter(anime -> anime.getId() == id) // Comparação correta para primitivo
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
   }

   public Anime save(Anime anime) {
      anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
      animes.add(anime);
      return anime;
   }

   public void delete(long id) {
      animes.remove(findById(id));
   }
}
