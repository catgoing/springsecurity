package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginDto {
	
	@Schema(name = "userId", defaultValue = "coca")
	private String userId;
	
	@Schema(name = "password", defaultValue = "qawsedrf12")
	private String password;

}
