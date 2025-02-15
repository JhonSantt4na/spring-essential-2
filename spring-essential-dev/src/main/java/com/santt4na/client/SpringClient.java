package com.santt4na.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.santt4na.domain.Anime;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
   public static void main(String[] args) {
      ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,
            5);
      log.info(entity);

      Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 5);
      log.info(object);

      // List of Animes with getForObjects
      Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
      log.info(Arrays.toString(animes));

      // List of Animes with exchange
      ResponseEntity<List<Anime>> responseEntity = new RestTemplate().exchange(
            "http://localhost:8080/animes/all",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Anime>>() {
            });

      log.info(responseEntity.getBody().toString());

      /*
       * Post Uando o ForObject
       * Anime kingdom = Anime.builder().name("Kingdom").build();
       * Anime kingdomSaved = new
       * RestTemplate().postForObject("http://localhost:8080/animes", kingdom,
       * Anime.class);
       * log.info("Saved Anime {}", kingdomSaved);
       */

      // Post Use the exchange
      Anime shampdlo = Anime.builder().name("Samuray Shamploo").build();
      ResponseEntity<Anime> shampdloSaved = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST,
            new HttpEntity<>(shampdlo, createdJsonHeaders()),
            Anime.class);
      log.info("Saved Anime {}", shampdloSaved);

   }

   private static HttpHeaders createdJsonHeaders() {
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
      return httpHeaders;
   }
}
