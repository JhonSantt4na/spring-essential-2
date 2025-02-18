package com.santt4na.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santt4na.Util.AnimeCreator;
import com.santt4na.Util.AnimePostRequireBodyCreator;
import com.santt4na.Util.AnimePutRequestBodyCreator;
import com.santt4na.domain.Anime;
import com.santt4na.exceptions.BadRequestException;
import com.santt4na.repository.AnimeRepository;
import com.santt4na.services.AnimeService;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTeste {

   @InjectMocks
   private AnimeService animeService;

   @Mock
   private AnimeRepository animeRepositoryMock;

   @BeforeEach // Antes de cada metodo passo um anime valido
   void setUp() {
      PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
      BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
            .thenReturn(animePage);

      BDDMockito.when(animeRepositoryMock.findAll())
            .thenReturn(List.of(AnimeCreator.createValidAnime()));

      BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

      BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.any()))
            .thenReturn(List.of(AnimeCreator.createValidAnime()));

      BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
            .thenReturn(AnimeCreator.createValidAnime());

      BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));

   }

   @Test
   @DisplayName("ListAll returns listPage of anime insider page when successfull")
   void ListAll_ReturnsListOfAnimeInsiderPageObject_WhenSuccessfull() {
      String expectedName = AnimeCreator.createValidAnime().getName();
      Page<Anime> animePage = animeService.listAll(PageRequest.of(1, 1));

      Assertions.assertThat(animePage).isNotNull();
      Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
      Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
   }

   @Test
   @DisplayName("ListAll returns list of anime when successfull")
   void ListAll_ReturnsListOfAnime_WhenSuccessfull() {
      String expectedName = AnimeCreator.createValidAnime().getName();
      List<Anime> animes = animeService.listAllNonPageable();

      Assertions.assertThat(animes)
            .isNotNull()
            .isNotEmpty()
            .hasSize(1);

      Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
   }

   @Test
   @DisplayName("findByIdOrThrowBadRequestException returns anime when successfull")
   void findByIdOrThrowBadRequestException_ReturnsListOfAnime_WhenSuccessfull() {
      Long expectedId = AnimeCreator.createValidAnime().getId();

      Anime anime = animeService.findByIdOrThrowBadRequestException(1);

      Assertions.assertThat(anime).isNotNull();

      Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
   }

   @Test
   @DisplayName("findByName returns List anime when successfull")
   void findByName_ReturnsListOfAnime_WhenSuccessfull() {
      String expectedName = AnimeCreator.createValidAnime().getName();

      List<Anime> animes = animeService.findByName("anime");

      Assertions.assertThat(animes)
            .isNotNull()
            .isNotEmpty()
            .hasSize(1);

      Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
   }

   @Test
   @DisplayName("findByName returns empty List anime when is not found")
   void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {

      BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.any()))
            .thenReturn(Collections.emptyList());

      List<Anime> animes = animeService.findByName("anime");

      Assertions.assertThat(animes)
            .isNotNull()
            .isEmpty();
   }

   @Test
   @DisplayName("Save returns anime when successfull")
   void save_ReturnsAnime_WhenSuccessfull() {
      Anime anime = animeService.save(AnimePostRequireBodyCreator.createAnimePostRequireBody());

      Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
   }

   @Test
   @DisplayName("Replace uptdated anime when successfull")
   void replace_UpdatesAnime_WhenSuccessfull() {
      Assertions.assertThatCode(
            () -> animeService.replace(AnimePutRequestBodyCreator.createAnimePutRequireBody()))
            .doesNotThrowAnyException();
   }

   @Test
   @DisplayName("delete Removes anime when successfull")
   void delete_RemovesAnime_WhenSuccessfull() {
      Assertions.assertThatCode(
            () -> animeService.delete(1L))
            .doesNotThrowAnyException();
   }

   @Test
   @DisplayName("findByIdOrThrowBadRequestException thors ThrowBadRequestException when is anime is not found")
   void findByIdOrThrowBadRequestException_ThrowBadRequestException_WhenIsAnimeIsNotFound() {

      BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.empty());

      Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> animeService.findByIdOrThrowBadRequestException(1L));

   }

}
