package com.santt4na.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.santt4na.Util.AnimeCreator;
import com.santt4na.Util.AnimePostRequireBodyCreator;
import com.santt4na.domain.Anime;
import com.santt4na.repository.AnimeRepository;
import com.santt4na.requests.AnimePostRequestBody;
import com.santt4na.wrapper.PageableResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

            PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null,
                        new ParameterizedTypeReference<PageableResponse<Anime>>() {
                        }).getBody();

            Assertions.assertThat(animePage).isNotNull();
            Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
            Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
      }

      @Test
      @DisplayName("ListAll returns list of anime when successfull")
      void ListAll_ReturnsListOfAnime_WhenSuccessfull() {
            Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());
            animeRepository.flush();
            String expectedName = savedAnime.getName();

            List<Anime> animes = testRestTemplate.exchange("/animes/all", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Anime>>() {
                        }).getBody();

            Assertions.assertThat(animes)
                        .isNotNull()
                        .isNotEmpty()
                        .hasSize(1);
            Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
      }

      @Test
      @DisplayName("findById returns anime when successfull")
      void findById_ReturnsListOfAnime_WhenSuccessfull() {
            Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());
            Long expectedId = savedAnime.getId();

            Anime anime = testRestTemplate.getForObject("/animes/{id}", Anime.class, expectedId);

            Assertions.assertThat(anime).isNotNull();

            Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
      }

      @Test
      @DisplayName("findByName returns List anime when successfull")
      void findByName_ReturnsListOfAnime_WhenSuccessfull() {
            Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());
            String expectedName = savedAnime.getName();

            String url = String.format("/animes/name?name=%s", expectedName);

            List<Anime> animes = testRestTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Anime>>() {
                        }).getBody();

            Assertions.assertThat(animes)
                        .isNotNull()
                        .isNotEmpty()
                        .hasSize(1);

            Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
      }

      @Test
      @DisplayName("findByName returns empty List anime when is not found")
      void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {

            List<Anime> animes = testRestTemplate.exchange("/animes/name?name=xyz", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Anime>>() {
                        }).getBody();

            Assertions.assertThat(animes)
                        .isNotNull()
                        .isEmpty();

      }

      @Test
      @DisplayName("Save returns anime when successfull")
      void save_ReturnsAnime_WhenSuccessfull() {
            AnimePostRequestBody animePostRequestBody = AnimePostRequireBodyCreator.createAnimePostRequireBody();

            ResponseEntity<Anime> animeResponseEntity = testRestTemplate.postForEntity("/animes", animePostRequestBody,
                        Anime.class);

            Assertions.assertThat(animeResponseEntity).isNotNull();
            Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            Assertions.assertThat(animeResponseEntity.getBody()).isNotNull();
            Assertions.assertThat(animeResponseEntity.getBody().getId()).isNotNull();
      }

      @Test
      @DisplayName("Replace uptdated anime when successfull")
      void replace_UpdatesAnime_WhenSuccessfull() {
            Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

            savedAnime.setName("Others");

            ResponseEntity<Void> animeResponseEntity = testRestTemplate.exchange("/animes", HttpMethod.PUT,
                        new HttpEntity<>(savedAnime),
                        Void.class);

            Assertions.assertThat(animeResponseEntity).isNotNull();
            Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

      }

      @Test
      @DisplayName("delete Removes anime when successfull")
      void delete_RemovesAnime_WhenSuccessfull() {
            Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

            ResponseEntity<Void> animeResponseEntity = testRestTemplate.exchange("/animes/{id}", HttpMethod.DELETE,
                        new HttpEntity<>(savedAnime),
                        Void.class, savedAnime.getId());

            Assertions.assertThat(animeResponseEntity).isNotNull();
            Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

      }
}