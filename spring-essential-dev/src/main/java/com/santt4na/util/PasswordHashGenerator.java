package com.santt4na.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashGenerator implements CommandLineRunner {

   @Override
   public void run(String... args) {
      String senhaFixa = "123456"; // Altere para a senha desejada
      String senhaCriptografada = new BCryptPasswordEncoder().encode(senhaFixa);
      System.out.println("========================================");
      System.out.println("Hash BCrypt para senha '" + senhaFixa + "':");
      System.out.println(senhaCriptografada);
      System.out.println("========================================");
   }
}