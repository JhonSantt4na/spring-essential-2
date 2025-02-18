package com.santt4na.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.santt4na.Util.AnimeCreator;
import com.santt4na.domain.Anime;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

//@DataJpaTest
@SpringBootTest
@DisplayName("Tests for Anime Repository")
@Log4j2
public class AnimeRepositoryTest {

   @Autowired
   private AnimeRepository animeRepository;

   @Test
   @DisplayName("Save Persist anime when Successful")
   void save_PersistAnime_WhenSuccessful() {

      Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
      Anime animeSaved = this.animeRepository.save(animeToBeSaved);

      Assertions.assertThat(animeSaved).isNotNull(); // Se não é null
      Assertions.assertThat(animeSaved.getId()).isNotNull(); // se foi salvo pq foi o banco que criou
      Assertions.assertThat(animeSaved.getName())
            .isEqualTo(animeToBeSaved.getName()); // nome do a ser salvo é igual ao do banco
   }

   @Test
   @DisplayName("Save updated anime when Successful")
   void save_UpdatedAnime_WhenSuccessful() {

      Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
      Anime animeSaved = this.animeRepository.save(animeToBeSaved);

      animeSaved.setName("Overlord");
      Anime animeUpdated = this.animeRepository.save(animeSaved);

      log.info(animeUpdated.getName());
      Assertions.assertThat(animeUpdated).isNotNull(); // Se não é null
      Assertions.assertThat(animeUpdated.getId()).isNotNull(); // se foi salvo pq foi o banco que criou
      Assertions.assertThat(animeUpdated.getName())
            .isEqualTo(animeSaved.getName()); // nome do a ser salvo é igual ao do banco
   }

   @Test
   @DisplayName("delete remove anime when Successful")
   void save_DeleteAnime_WhenSuccessful() {

      Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
      Anime animeSaved = this.animeRepository.save(animeToBeSaved);

      this.animeRepository.delete(animeSaved);

      Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());
      Assertions.assertThat(animeOptional).isEmpty();
   }

   @Test
   @DisplayName("find by name return List of anime when Successful")
   void findByName_ReturnListOfAnime_WhenSuccessful() {

      Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
      Anime animeSaved = this.animeRepository.save(animeToBeSaved);

      String name = animeSaved.getName();

      List<Anime> animes = this.animeRepository.findByName(name);

      Assertions.assertThat(animes).isNotEmpty().contains(animeSaved);
   }

   @Test
   @DisplayName("find by name return List empty when no anime is found")
   void save_ReturnEmptyList_WhenAnimeIsNotFound() {

      List<Anime> animes = this.animeRepository.findByName("kk");
      Assertions.assertThat(animes).isEmpty();
   }

   @Test
   @DisplayName("Save Throw ConstraintViolationException when name is empty")
   void save_ThrowConstraintViolationException_WhenNameIsEmpty() {

      Anime anime = new Anime();
      // Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
      // .isInstanceOf(ConstraintViolationException.class);

      Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> this.animeRepository.save(anime))
            .withMessageContaining("Anime name cannot be null or empty");

   }
}
