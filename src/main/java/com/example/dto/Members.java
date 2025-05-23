package com.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "members", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"}, name = "members_unique")})
public class Members {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String role;

    @Column(nullable = false, length = 30)
    private String userId;
    
    @Column(nullable = false, length = 1000)
    private String password;
    
    @Column(nullable = false, length = 30)
    private String name;
    
}
