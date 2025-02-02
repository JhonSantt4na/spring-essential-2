package spring.santt4na.spring_introduction.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.santt4na.spring_introduction.domain.Anime;

@RestController // Indica que essa classe é um controlador REST
@RequestMapping("anime") // Define o endpoint base (localhost/anime)
public class AnimeController {

   @GetMapping("list") // Define um endpoint GET em (localhost/anime/list)
   public List<Anime> list() {
      return List.of(
            new Anime("Attack on Titan"),
            new Anime("Naruto"),
            new Anime("Dragon Ball Z"));
   }
}
