package com.santt4na.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                        .authorizeHttpRequests(auth -> auth
                                    .requestMatchers(
                                                "/public/**",
                                                "/login", // Permite acesso ao endpoint de login
                                                "/logout" // Permite acesso ao endpoint de logout
                                    ).permitAll()
                                    .anyRequest().authenticated())
                        .formLogin(form -> form // Remove .loginPage("/login") para usar a página padrão
                                    .defaultSuccessUrl("/animes", true) // Redireciona para um endpoint válido após
                                                                        // login
                        )
                        .logout(logout -> logout
                                    .logoutSuccessUrl("/login?logout") // Mantém o redirecionamento pós-logout
                                    .permitAll());

            return http.build();
      }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
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