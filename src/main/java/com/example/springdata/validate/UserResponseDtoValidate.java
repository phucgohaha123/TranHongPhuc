package com.example.springdata.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springdata.dto.UserRequestBodyLoginDto;
import com.example.springdata.dto.UserRequestDto;
import com.example.springdata.service.UsersService;

public class UserResponseDtoValidate {
	@Autowired
	private static UsersService usersService;
	private String email;
	private String password;
	private String firstName;
	private String lastName;

	public static Map<String, String> validate(UserRequestDto requestDto) {
		Map<String, String> errors = new HashMap<>();
		if(requestDto.getEmail()==null||requestDto.getEmail().isEmpty()||250<requestDto.getEmail().length()) {
			errors.put("email", "email khong duoc rong va tu duoi 250 ki tu ");
		}
		if(requestDto.getPassword()==null||8>requestDto.getPassword().length()||20<requestDto.getPassword().length()) {
			errors.put("password", "mat khau giua 8 va 20 ki tu");
		}
		return errors;
	}
	
	public static Map<String, String> validate(UserRequestBodyLoginDto requestDto) {
		Map<String, String> errors = new HashMap<>();
		if(requestDto.getEmail()==null||requestDto.getEmail().isEmpty()||250<requestDto.getEmail().length()) {
			errors.put("email", "email khong duoc rong va tu duoi 250 ki tu ");
		}
		if(requestDto.getPassword()==null||8>requestDto.getPassword().length()||20<requestDto.getPassword().length()) {
			errors.put("password", "mat khau giua 8 va 20 ki tu");
		}
		return errors;
	}
	
	public static Map<String, String> responseExsistEmail(UserRequestDto requestDto) {
		Map<String, String> errors = new HashMap<>();
		errors.put("email", "email da duoc dang ki");
		return errors;
	}
	
	public UserResponseDtoValidate(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static UserResponseDtoValidate userResponse(UserRequestDto requestDto) {
		return new UserResponseDtoValidate(requestDto.getEmail(), requestDto.getPassword(),requestDto.getFirstName(), requestDto.getFirstName());
	}
	
}
