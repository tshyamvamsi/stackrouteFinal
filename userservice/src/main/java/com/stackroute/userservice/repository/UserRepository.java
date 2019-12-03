package com.stackroute.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userservice.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

	public User findByUserIdAndPassword(String userId, String password);
}
