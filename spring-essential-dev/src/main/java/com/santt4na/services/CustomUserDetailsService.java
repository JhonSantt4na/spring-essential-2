package com.santt4na.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santt4na.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

      @Autowired
      private UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String username) {
            return Optional.ofNullable(userRepository.findByUsername(username))
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
      }
}
