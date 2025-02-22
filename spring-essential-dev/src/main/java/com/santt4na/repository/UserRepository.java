package com.santt4na.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santt4na.domain.Santt4naUser;

@Repository
public interface UserRepository extends JpaRepository<Santt4naUser, Long> {
   Santt4naUser findByUsername(String username);
}
