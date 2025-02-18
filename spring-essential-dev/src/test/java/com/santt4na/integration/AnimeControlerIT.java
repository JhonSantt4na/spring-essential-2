package com.santt4na.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;

import com.santt4na.Util.AnimeCreator;
import com.santt4na.domain.Anime;
import com.santt4na.repository.AnimeRepository;
import com.santt4na.wrapper.PageableResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AnimeControlerIT {

   @Autowired
   private TestRestTemplate testRestTemplate;

   @Autowired
   private AnimeRepository animeRepository;

   @LocalServerPort
   private int port;

   @Test
   @DisplayName("List returns listPage of anime insider page when successfull")
   void List_ReturnsListOfAnimeInsiderPageObject_WhenSuccessfull() {
      Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());
      animeRepository.flush();
      String expectedName = savedAnime.getName();

      System.out.println("🛠️ Animes no H2 antes do GET:");
      animeRepository.findAll().forEach(anime -> System.out.println("📌 " + anime));

      PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null,
            new ParameterizedTypeReference<PageableResponse<Anime>>() {
            }).getBody();

      Assertions.assertThat(animePage).isNotNull();
      Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
      Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
   }
}