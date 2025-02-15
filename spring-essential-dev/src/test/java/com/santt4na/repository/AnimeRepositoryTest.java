package com.santt4na.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.santt4na.domain.Anime;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
public class AnimeRepositoryTest {

   @Autowired
   private AnimeRepository animeRepository;

   @Test
   @DisplayName("Save creates anime when Successful")
   void save_PersistAnime_WhenSuccessful() {

      Anime animeToBeSaved = createAnime();
      Anime animeSaved = this.animeRepository.save(animeToBeSaved);

      Assertions.assertThat(animeSaved).isNotNull(); // Se não é null
      Assertions.assertThat(animeSaved.getId()).isNotNull(); // se foi salvo pq foi o banco que criou
      Assertions.assertThat(animeSaved.getName())
            .isEqualTo(animeToBeSaved.getName()); // nome do a ser salvo é igual ao do banco
   }

   private Anime createAnime() {
      return Anime.builder()
            .name("Boruto")
            .build();
   }
}
