package com.santt4na.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santt4na.domain.Anime;
import com.santt4na.services.AnimeService;
import com.santt4na.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
      // HttpStatus.OK informa o status code
      // ResponseEntity = Siqnificado aqui
   }

   // tendo 2 get no mesmo com vamos dizer a diferença

   @GetMapping(path = "/{id}")
   public ResponseEntity<Anime> findById(@PathVariable long id) {
      return ResponseEntity.ok(animeService.findById(id));
   }

   // Se tiver somente um o spring vai mapear automaticamente todas as requisição
   // post pra esse methodo.
   @PostMapping
   // @ResponseStatus(HttpStatus.CREATED) forma opcional
   public ResponseEntity<Anime> save(@RequestBody Anime anime) {
      // @JsonPropeties("exemplo") Caso não tenha mapeado corretamente ele ignora

      return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
      /*
       * Existe muitos lugares que voce so recebe uma resosta 201
       * Existe padrão que voce retorna o objeto
       * Existe Padrão que vc retorna somente um id do item criado
       */
   }

}
