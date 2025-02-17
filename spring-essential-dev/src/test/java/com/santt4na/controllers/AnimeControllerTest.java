package com.santt4na.controllers;

import java.util.Collections;
import java.util.List;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santt4na.Util.AnimeCreator;
import com.santt4na.Util.AnimePostRequireBodyCreator;
import com.santt4na.Util.AnimePutRequestBodyCreator;
import com.santt4na.domain.Anime;
import com.santt4na.requests.AnimePostRequestBody;
import com.santt4na.requests.AnimePutRequestBody;
import com.santt4na.services.AnimeService;

@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

      @InjectMocks // Quando for testar a classe
      private AnimeController animeController;

      @Mock // Para todas as classes dentro da de cima
      private AnimeService animeServiceMock;

      @BeforeEach // Antes de cada metodo passo um anime valido
      void setUp() {
            PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
            BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                        .thenReturn(animePage);

            BDDMockito.when(animeServiceMock.listAllNonPageable())
                        .thenReturn(List.of(AnimeCreator.createValidAnime()));

            BDDMockito.when(animeServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                        .thenReturn(AnimeCreator.createValidAnime());

            BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.any()))
                        .thenReturn(List.of(AnimeCreator.createValidAnime()));

            BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimePostRequestBody.class)))
                        .thenReturn(AnimeCreator.createValidAnime());

            BDDMockito.doNothing().when(animeServiceMock).replace(ArgumentMatchers.any(AnimePutRequestBody.class));
            BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());

      }

      @Test
      @DisplayName("List returns listPage of anime insider page when successfull")
      void List_ReturnsListOfAnimeInsiderPageObject_WhenSuccessfull() {
            String expectedName = AnimeCreator.createValidAnime().getName();
            Page<Anime> animePage = animeController.list(null).getBody();

            Assertions.assertThat(animePage).isNotNull();
            Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
            Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
      }

      @Test
      @DisplayName("ListAll returns list of anime when successfull")
      void ListAll_ReturnsListOfAnime_WhenSuccessfull() {
            String expectedName = AnimeCreator.createValidAnime().getName();
            List<Anime> animes = animeController.listAll().getBody();

            Assertions.assertThat(animes)
                        .isNotNull()
                        .isNotEmpty()
                        .hasSize(1);

            Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
      }

      @Test
      @DisplayName("findById returns anime when successfull")
      void findById_ReturnsListOfAnime_WhenSuccessfull() {
            Long expectedId = AnimeCreator.createValidAnime().getId();

            Anime anime = animeController.findById(1).getBody();

            Assertions.assertThat(anime).isNotNull();

            Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
      }

      @Test
      @DisplayName("findByName returns List anime when successfull")
      void findByName_ReturnsListOfAnime_WhenSuccessfull() {
            String expectedName = AnimeCreator.createValidAnime().getName();

            List<Anime> animes = animeController.findByName("anime").getBody();

            Assertions.assertThat(animes)
                        .isNotNull()
                        .isNotEmpty()
                        .hasSize(1);

            Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
      }

      @Test
      @DisplayName("findByName returns empty List anime when is not found")
      void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {

            BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.any()))
                        .thenReturn(Collections.emptyList());

            List<Anime> animes = animeController.findByName("anime").getBody();

            Assertions.assertThat(animes)
                        .isNotNull()
                        .isEmpty();
      }

      @Test
      @DisplayName("Save returns anime when successfull")
      void save_ReturnsAnime_WhenSuccessfull() {
            Anime anime = animeController.save(AnimePostRequireBodyCreator.createAnimePostRequireBody()).getBody();

            Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
      }

      @Test
      @DisplayName("Replace uptdated anime when successfull")
      void replace_UpdatesAnime_WhenSuccessfull() {
            Assertions.assertThatCode(
                        () -> animeController.replace(AnimePutRequestBodyCreator.createAnimePutRequireBody()))
                        .doesNotThrowAnyException();
            ResponseEntity<Void> entity = animeController
                        .replace(AnimePutRequestBodyCreator.createAnimePutRequireBody());
            Assertions.assertThat(entity).isNotNull();
            Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

      }

      @Test
      @DisplayName("delete Removes anime when successfull")
      void delete_RemovesAnime_WhenSuccessfull() {
            Assertions.assertThatCode(
                        () -> animeController.delete(1L))
                        .doesNotThrowAnyException();

            ResponseEntity<Void> entity = animeController.delete(1);
            Assertions.assertThat(entity).isNotNull();
            Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

      }

}
