package com.santt4na.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimePostRequestBody {
   // @NotNull(message = "Anime name cannot be null")
   @NotEmpty(message = "Anime name cannot be empty") // Já Olha o Null e empty
   // @Url, @Numeros ...
   private String name;
}
