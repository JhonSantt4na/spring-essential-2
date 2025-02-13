package com.santt4na.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnimePostRequestBody {
   // @NotNull(message = "Anime name cannot be null")
   @NotEmpty(message = "Anime name cannot be null or empty")
   private String name;

}
