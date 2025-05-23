package com.example.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDetails implements UserDetails {

	private static final long serialVersionUID = -5038010265949609363L;
	private List<GrantedAuthority> authorities;
	private String password;
	private String username;
	
	public MemberDetails(String userId, String password) {
		this.password = password;
		this.username = userId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
