package com.example.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.config.MemberDetails;
import com.example.dto.Members;
import com.example.exception.BizException;
import com.example.repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MembersRepository membersRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Members members = membersRepository.findByUserId(userId).orElseThrow(() -> new BizException(userId));
		return new MemberDetails(members.getUserId(), members.getPassword());
	}
}