package com.santt4na.controllers;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santt4na.Util.AnimeCreator;
import com.santt4na.domain.Anime;
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
   }

   @Test
   @DisplayName("List returns list of anime insider page when successfull")
   void List_ReturnsListOfAnimeInsiderPageObject_WhenSuccessfull() {
      String expectedName = AnimeCreator.createValidAnime().getName();
      Page<Anime> animePage = animeController.list(null).getBody();

      Assertions.assertThat(animePage).isNotNull();
      Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
      Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
   }
}
