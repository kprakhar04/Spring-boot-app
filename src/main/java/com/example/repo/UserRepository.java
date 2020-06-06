package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.*;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByEmail(String email);
}
