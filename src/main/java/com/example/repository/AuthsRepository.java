package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.Auths;
import com.example.dto.AuthsId;

@Repository
public interface AuthsRepository extends JpaRepository<Auths, AuthsId> {
	
	public Optional<Auths> findByUserId(String userId);

}
