package com.santt4na.Util;

import com.santt4na.domain.Anime;

public class AnimeCreator {

   public static Anime createAnimeToBeSaved() {
      return Anime.builder()
            .name("Boruto")
            .build();
   }

   public static Anime createValidAnime() {
      return Anime.builder()
            .name("Boruto")
            .id(1L)
            .build();
   }

   public static Anime createUpdatedAnime() {
      return Anime.builder()
            .name("CDZ Aweikining")
            .id(1L)
            .build();
   }

}
