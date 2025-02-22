package com.santt4na.requests;

import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(description = "This is Anime's name", example = "Dragon Ball Z", required = true)
   private String name;

}
