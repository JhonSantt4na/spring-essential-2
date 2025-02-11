package com.santt4na.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

   // First look if in Data base, Engine = InnoDB

   /*
    * Por Padrão o @Trasactional ele so retorna o roolback
    * para exceptions do tipo RuntimeException
    * Para mudar usamos o (rollbackFor = Exception.class)
    */
   @Transactional // when we add this anotation, the Spring It only ends if everything goes well.
   public Anime save(AnimePostRequestBody animePostRequestBody) {
      // Simulating an exception
      Anime save = animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
      if (true) {
         // even with an error the Database saves the object
         throw new RuntimeException("Bad Code !");
      }
      return save;
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
