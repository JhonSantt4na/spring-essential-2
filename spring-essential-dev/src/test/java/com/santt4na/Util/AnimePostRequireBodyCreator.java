package com.santt4na.Util;

import com.santt4na.requests.AnimePostRequestBody;

public class AnimePostRequireBodyCreator {
   public static AnimePostRequestBody createAnimePostRequireBody() {
      return AnimePostRequestBody.builder()
            .name(AnimeCreator.createAnimeToBeSaved().getName())
            .build();
   }
}
