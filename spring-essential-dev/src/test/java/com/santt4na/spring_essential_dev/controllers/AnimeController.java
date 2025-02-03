package com.santt4na.spring_essential_dev.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santt4na.spring_essential_dev.domain.Anime;
import com.santt4na.spring_essential_dev.services.AnimeService;
import com.santt4na.spring_essential_dev.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

   private final DateUtil dateUtil;
   private final AnimeService animeService;

   @GetMapping
   public List<Anime> list() {
      log.info(dateUtil.formatLocalTimeDataBaseStyle(LocalDateTime.now()));
      return animeService.listAll();
   }
}
