package com.santt4na.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santt4na.domain.Anime;
import com.santt4na.exceptions.BadRequestException;
import com.santt4na.mapper.AnimeMapper;
import com.santt4na.repository.AnimeRepository;
import com.santt4na.requests.AnimePostRequestBody;
import com.santt4na.requests.AnimePutRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {

   private final AnimeRepository animeRepository;

   public List<Anime> listAll() {
      return animeRepository.findAll();
   }

   public Anime findByIdOrThrowBadRequestException(long id) {
      return animeRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Anime not found"));
   }

   @Transactional
   public Anime save(AnimePostRequestBody animePostRequestBody) {
      // Vamos adiconar a dependencia do no pom.xml
      /*
       * validação manual:
       * if (animePostRequestBody.getName() == null) {
       * thorw new RuntimeException("Name cannot be null")
       * }
       */
      return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
   }

   public void delete(long id) {
      animeRepository.delete(findByIdOrThrowBadRequestException(id));
   }

   public void replace(AnimePutRequestBody animePutRequestBody) {
      Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
      Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
      anime.setId(savedAnime.getId());
      animeRepository.save(anime);
   }

   public List<Anime> findByName(String name) {
      return animeRepository.findByName(name);
   }
}
