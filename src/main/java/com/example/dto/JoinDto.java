package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JoinDto {
	
	@Schema(name = "userId", defaultValue = "testId")
	private String userId;
	
	@Schema(name = "password", defaultValue = "password")
	private String password;
	
	@Schema(name = "name", defaultValue = "name")
	private String name;

}
