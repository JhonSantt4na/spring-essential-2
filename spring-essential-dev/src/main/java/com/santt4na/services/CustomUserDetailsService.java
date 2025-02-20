package com.santt4na.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.santt4na.domain.Santt4naUser;
import com.santt4na.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Santt4naUser user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
      return user.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .authorites(user.getAuthorites()).build();
   }

}
