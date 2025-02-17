package com.santt4na.Util;

import com.santt4na.requests.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {
   public static AnimePutRequestBody createAnimePutRequireBody() {
      return AnimePutRequestBody.builder()
            .id(AnimeCreator.createUpdatedAnime().getId())
            .name(AnimeCreator.createUpdatedAnime().getName())
            .build();
   }
}
