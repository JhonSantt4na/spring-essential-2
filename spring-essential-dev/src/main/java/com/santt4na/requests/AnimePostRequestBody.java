package com.santt4na.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
   // @NotNull(message = "Anime name cannot be null")
   @NotEmpty(message = "Anime name cannot be null or empty")
   private String name;

}
