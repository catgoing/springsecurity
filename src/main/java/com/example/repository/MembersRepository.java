package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
	
	public Optional<Members> findByUserId(String userId);
	
	public boolean existsByUserId(String userId);
	
}
