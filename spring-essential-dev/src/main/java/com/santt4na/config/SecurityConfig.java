package com.santt4na.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.santt4na.services.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

      private final CustomUserDetailsService userDetailsService;

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // Configuração do AuthenticationManagerBuilder
            AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
            auth.userDetailsService(userDetailsService)
                        .passwordEncoder(passwordEncoder());

            // Exibe a senha codificada no console
            System.out.println("Password encoded: " + passwordEncoder().encode("Teste123"));

            http
                        .csrf(Customizer.withDefaults())
                        .authorizeHttpRequests(authorize -> authorize
                                    .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                        .formLogin(Customizer.withDefaults());

            return http.build();
      }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
      }
}
// Exemplo de configuração de usuários em memória (comentado)
/*
 * @Bean
 * public InMemoryUserDetailsManager userDetailsService() {
 * PasswordEncoder encoder =
 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
 * 
 * // Cria um usuário com nome, senha e role
 * UserDetails user = User.builder()
 * .username("santt4na")
 * .password(encoder.encode("1234"))
 * .roles("USER")
 * .build();
 * 
 * UserDetails admin = User.builder()
 * .username("jorge")
 * .password(encoder.encode("4567"))
 * .roles("ADMIN")
 * .build();
 * 
 * return new InMemoryUserDetailsManager(user, admin); // Retorna o gerenciador
 * de usuários em memória
 * }
 */