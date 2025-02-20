package com.santt4na.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santt4na.domain.Anime;
import com.santt4na.requests.AnimePostRequestBody;
import com.santt4na.requests.AnimePutRequestBody;
import com.santt4na.services.AnimeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

   private final AnimeService animeService;

   // Pegando as duas Roles
   // @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
   @PreAuthorize("hasRole('ADMIN')")
   // @PreAuthorize("hasRole('ADMIN')") // Pegando somente 1
   @GetMapping
   public ResponseEntity<Page<Anime>> list(Pageable pageable) {
      return new ResponseEntity<>(animeService.listAll(pageable), HttpStatus.OK);
   }

   @GetMapping(path = "/all")
   public ResponseEntity<List<Anime>> listAll() {
      return new ResponseEntity<>(animeService.listAllNonPageable(), HttpStatus.OK);
   }

   @GetMapping(path = "/{id}")
   public ResponseEntity<Anime> findById(@PathVariable long id) {
      return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
   }

   @GetMapping(path = "by-id/{id}")
   public ResponseEntity<Anime> findIdAutenticationPrincipal(@PathVariable long id,
         // Anotação para pegar quem esta autenticado + Tipo usado para os usuarios
         @AuthenticationPrincipal UserDetails userDetails) {
      log.info(userDetails);
      return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
   }

   @GetMapping(path = "/name")
   public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
      return ResponseEntity.ok(animeService.findByName(name));
   }

   @PostMapping // @Valid Para adicionar a nossa validação
   // @PreAuthorize("hasHole('ADMIN')")
   public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
      return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Void> delete(@PathVariable long id) {
      animeService.delete(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @PutMapping
   public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
      animeService.replace(animePutRequestBody);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}