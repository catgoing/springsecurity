package com.example.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class AuthsId implements Serializable {
	@Column(nullable = false)
    private Long id;
	
	@Column(nullable = false, length = 30)
    private String userId;
	
	@Column(nullable = false, length = 1000)
    private String url;
}