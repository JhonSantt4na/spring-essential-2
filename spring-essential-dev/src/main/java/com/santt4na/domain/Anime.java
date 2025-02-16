package com.santt4na.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Anime {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   // Validação de Campos
   // @Column(nullable = false) Não funciona, não atualiza no DB

   @NotEmpty(message = "Anime name cannot be null or empty")
   private String name;
}
