package com.santt4na.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.santt4na.domain.Anime;
import com.santt4na.requests.AnimePostRequestBody;
import com.santt4na.requests.AnimePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
   public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

   public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

   public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
