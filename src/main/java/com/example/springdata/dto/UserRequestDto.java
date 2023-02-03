package com.example.springdata.dto;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springdata.entity.Users;

public class UserRequestDto {

	private String email;

	private String password;

	private String firstName;

	private String lastName;
	
	public UserRequestDto(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static Users convertFormUser(UserRequestDto userRequestDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		Users user = new Users(userRequestDto.firstName, userRequestDto.lastName, userRequestDto.email,passwordEncoder.encode(userRequestDto.getPassword()), LocalDateTime.now(), LocalDateTime.now());
		return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
