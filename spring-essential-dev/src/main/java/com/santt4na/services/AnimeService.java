package com.santt4na.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

   public Page<Anime> listAll(Pageable pageable) {
      return animeRepository.findAll(pageable); // findAll(pageable) Vem do PagingAndSortingRepository que nosso
      // repository extends ...
      // http://localhost:8080/animes?size=5 --> Pega somente 5 item
      // http://localhost:8080/animes?size=5&page=1 --> pega a pagina 2
   }

   public List<Anime> listAllNonPageable() {
      return animeRepository.findAll();
   }

   public Anime findByIdOrThrowBadRequestException(long id) {
      return animeRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Anime not found"));
   }

   @Transactional
   public Anime save(AnimePostRequestBody animePostRequestBody) {
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
