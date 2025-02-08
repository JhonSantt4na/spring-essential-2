package com.santt4na.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santt4na.domain.Anime;
import com.santt4na.requests.AnimePostRequestBody;
import com.santt4na.requests.AnimePutRequestBody;
import com.santt4na.services.AnimeService;
import com.santt4na.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

   private final DateUtil dateUtil;
   private final AnimeService animeService;

   @GetMapping
   public ResponseEntity<List<Anime>> list() {
      log.info(dateUtil.formatLocalTimeDataBaseStyle(LocalDateTime.now()));
      return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
   }

   @GetMapping(path = "/{id}")
   public ResponseEntity<Anime> findById(@PathVariable long id) {
      return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
   }

   @PostMapping
   public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody) {
      return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Anime> delete(@PathVariable long id) {
      animeService.delete(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @PutMapping
   public ResponseEntity<Anime> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
      animeService.replace(animePutRequestBody);
     
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}