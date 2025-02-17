package com.santt4na.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePostRequestBody {
   // @NotNull(message = "Anime name cannot be null")
   @NotEmpty(message = "Anime name cannot be null or empty")
   private String name;

}
