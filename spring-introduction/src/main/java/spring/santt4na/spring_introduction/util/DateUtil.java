package spring.santt4na.spring_introduction.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/*
 * Quando criamos uma classe e queremos que ela seja auto injetada em outra classe
 * Precisamos usar uma anotação que indica isso para o spring
 * @Component -> 
 * @Service ->
 * @Repository ->
 * 
 * Sendo que a diferença é que algumas são mais especializadas 
 * por exemplo @Repository é muito usado para bancos de dados pois tem 
 * mais exceções para isso
 * 
 */

@Component // Nosso DateUtil se caracteriza como Componente.
public class DateUtil {
   public String formatLocalTimeDataBaseStyle(LocalDateTime localDateTime) {
      return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
   }
}
