package com.example.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.JoinDto;
import com.example.dto.LoginDto;
import com.example.dto.Members;
import com.example.repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersService {
	
	private final MembersRepository membersRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public Members getMember(LoginDto user) {
		Members member = membersRepository.findByUserId(user.getUserId()).orElse(null);
		return membersRepository.findByUserId(user.getUserId()).orElse(null);
	}
	
	public List<Members> getMembers() {
		return membersRepository.findAll();
	}
	
	public Members save(JoinDto user) throws Exception {
		
		if(membersRepository.existsByUserId(user.getUserId())) {
			throw new Exception("이미 존재하는 사용자ID입니다.");
		}
		
		Members m = new Members();
		m.setUserId(user.getUserId());
		m.setPassword(passwordEncoder.encode(user.getPassword()));
		m.setName(user.getName());
		m.setRole("ROLE_USER");
		membersRepository.save(m);
		
		return m;
		
	}

}
