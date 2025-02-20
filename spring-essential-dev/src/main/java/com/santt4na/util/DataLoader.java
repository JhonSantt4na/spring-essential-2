package com.santt4na.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.santt4na.domain.Santt4naUser;
import com.santt4na.repository.UserRepository;

@Configuration
public class DataLoader {

   @Bean
   public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
      return args -> {
         // Cria um usuário comum
         Santt4naUser user = new Santt4naUser();
         user.setUsername("santt4na");
         user.setPassword(passwordEncoder.encode("1234"));
         user.setAuthorites("USER");
         userRepository.save(user);

         // Cria um administrador
         Santt4naUser admin = new Santt4naUser();
         admin.setUsername("jorge");
         admin.setPassword(passwordEncoder.encode("4567"));
         admin.setAuthorites("ADMIN");
         userRepository.save(admin);
      };
   }
}