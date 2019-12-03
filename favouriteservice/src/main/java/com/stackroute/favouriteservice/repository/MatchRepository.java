package com.stackroute.favouriteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.favouriteservice.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {

	List<Match> findByUserId(String userId);


}
