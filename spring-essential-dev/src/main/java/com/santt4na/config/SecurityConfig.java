package com.santt4na.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                        .csrf(Customizer.withDefaults()) // Configura proteção CSRF
                        .authorizeHttpRequests(authorize -> authorize
                                    .anyRequest().authenticated() // Todas as requisições precisam de autenticação
                        )
                        .httpBasic(Customizer.withDefaults()) // Habilita autenticação básica
                        .formLogin(Customizer.withDefaults()); // Habilita formulário de login padrão

            return http.build();
      }

      // @Bean
      // public InMemoryUserDetailsManager userDetailsService() {
      // PasswordEncoder encoder =
      // PasswordEncoderFactories.createDelegatingPasswordEncoder();

      // // Cria um usuário com nome, senha e role
      // // UserDetails user = User.builder()
      // // .username("santt4na")
      // // .password(encoder.encode("1234"))
      // // .roles("USER")
      // // .build();

      // UserDetails admin = User.builder()
      // .username("jorge")
      // .password(encoder.encode("4567"))
      // .roles("ADMIN")
      // .build();

      // return new InMemoryUserDetailsManager(admin); // Retorna o gerenciador de
      // usuários em memória
      // }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
      }
}