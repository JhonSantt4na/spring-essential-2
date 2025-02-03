package com.santt4na.spring_essential_dev.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santt4na.spring_essential_dev.domain.Anime;
import com.santt4na.spring_essential_dev.util.DateUtil;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("anime")
@Log4j2
public class AnimeController {

   private DateUtil dateUtil;

   public AnimeController(DateUtil dateUtil) {
      this.dateUtil = dateUtil;
   }

   @GetMapping("list")
   public List<Anime> list() {
      log.info(dateUtil.formatLocalTimeDataBaseStyle(LocalDateTime.now()));
      return List.of(
            new Anime("Attack on Titan"),
            new Anime("Naruto"),
            new Anime("Dragon Ball Z"));
   }
}
