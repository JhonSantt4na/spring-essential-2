package spring.santt4na.spring_introduction.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import spring.santt4na.spring_introduction.domain.Anime;
import spring.santt4na.spring_introduction.util.DateUtil;

@RestController // Indica que essa classe é um controlador REST
@RequestMapping("anime") // Define o endpoint base (localhost/anime)
@Log4j2
public class AnimeController {

   /*
    * Não deve ultilizar Autowired no campo diretamente (Dificulta os
    * teste, e questões de Segurança)
    * 
    */
   // @Autowired
   // @AllArgsContructor - Cria um construtor com todos os args que temos
   // @RequiredArgsConstructor - add o final no nosso atributo DateUtil pois com
   // essa anotação so pega os campos com o final
   private DateUtil dateUtil;

   // Vamos criar um construtor e colocar ele faz a mesma coisa temos que remover o
   // @Autowired e deixar apenas o contrutor
   public AnimeController(DateUtil dateUtil) {
      this.dateUtil = dateUtil;
   }

   @GetMapping("list") // Define um endpoint GET em (localhost/anime/list)
   public List<Anime> list() {
      log.info(dateUtil.formatLocalTimeDataBaseStyle(LocalDateTime.now()));
      return List.of(
            new Anime("Attack on Titan"),
            new Anime("Naruto"),
            new Anime("Dragon Ball Z"));
   }
}
