package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CommonResponse;
import com.example.dto.JoinDto;
import com.example.dto.LoginDto;
import com.example.dto.Members;
import com.example.service.Excel;
import com.example.service.MembersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final MembersService membersService;
	private final Excel excelService;
	
	@PostMapping("/login")
	public CommonResponse<Members> login(@RequestBody LoginDto user) {
		return CommonResponse.ok(membersService.getMember(user));
	}
	
	@PostMapping("/login2")
	public CommonResponse<Members> login2(@RequestBody LoginDto user) {
		return CommonResponse.ok(membersService.getMember(user));
	}
	
	@PostMapping("/signup")
	public CommonResponse<Members> signup(@RequestBody JoinDto user) throws Exception {
		Members member = membersService.save(user);
		return CommonResponse.ok(member);
	}
	
	@GetMapping("/members")
	public CommonResponse<List<Members>> getAllMembers() throws Exception {
		List<Members> member = membersService.getMembers();
		return CommonResponse.ok(member);
	}
	
	@GetMapping("/auths")
	public CommonResponse<List<Members>> getAuths() throws Exception {
		List<Members> member = membersService.getMembers();
		return CommonResponse.ok(member);
	}
	
	@PostMapping("/excel")
	public CommonResponse<Object> excel(@RequestBody String url) {
		
		excelService.run(url);
		
		return CommonResponse.ok();
	}
}
