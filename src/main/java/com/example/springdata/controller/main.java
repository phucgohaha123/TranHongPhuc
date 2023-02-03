package com.example.springdata.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdata.dto.LoginResponseDto;
import com.example.springdata.dto.ResponseDto;
import com.example.springdata.dto.UserRequestBodyLoginDto;
import com.example.springdata.dto.UserRequestDto;
import com.example.springdata.entity.Tokens;
import com.example.springdata.entity.Users;
import com.example.springdata.security.JwtTokenProvider;
import com.example.springdata.service.UsersService;
import com.example.springdata.validate.Error;
import com.example.springdata.validate.UserResponseDtoValidate;
import com.example.springdata.service.TokensService;

@RestController
public class main {
	@Autowired
	TokensService theloai;
	@Autowired
	UsersService usersService;
	@Autowired
	TokensService tokensService;
	@Autowired
    private JwtTokenProvider tokenProvider;
	@Autowired
    AuthenticationManager authenticationManager;
	
	/**
	 * SignUp user.
	 * @param user
	 * @param bindingResult
	 * @return user
	 */
	@PostMapping("/sign-up")
	public ResponseEntity<Object> signUp(@RequestBody UserRequestDto user) {
			//kiem tra requestbody
			if(UserResponseDtoValidate.validate(user).size()>0) {
				return new ResponseEntity<Object>(UserResponseDtoValidate.validate(user), HttpStatus.BAD_REQUEST);
			}
			System.out.println(usersService.checkExsistEmail(user.getEmail()));
			//kiem tra email trung
			if(usersService.checkExsistEmail(user.getEmail())>0) {
				return new ResponseEntity<Object>(UserResponseDtoValidate.responseExsistEmail(user), HttpStatus.BAD_REQUEST);
			}
			usersService.save(UserRequestDto.convertFormUser(user));
		return ResponseEntity.ok(ResponseDto.convertFromRequestDto(user,usersService.getLastUserIdInsert()+1));
	}
	
	@PostMapping("/sign-in")
	public ResponseEntity<Object> signIn(@RequestBody UserRequestBodyLoginDto user) throws Throwable {
			//kiem tra requestbody
			if(UserResponseDtoValidate.validate(user).size()>0) {
				return new ResponseEntity<Object>(UserResponseDtoValidate.validate(user), HttpStatus.BAD_REQUEST);
			}
			// Xac thuc dang nhap
			
			if(usersService.getUserByEmail(user.getEmail())!=null) {
				Users usersInfo = usersService.getUserByEmail(user.getEmail());
				BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  
				boolean isPasswordMatches = bcrypt.matches(user.getPassword(), usersService.getUserByEmail(user.getEmail()).getPassword());
				System.out.println(isPasswordMatches);
				if(isPasswordMatches) {
					String jwt = tokenProvider.generateToken(usersService.getUserByEmail(user.getEmail()),1800000L);
					String refreshJwt = tokenProvider.generateToken(usersService.getUserByEmail(user.getEmail()),2592000000L);
					Tokens tokens =new Tokens(usersService.getUserByEmail(user.getEmail()), refreshJwt, "30 ngay", LocalDateTime.now(), LocalDateTime.now());
					tokensService.save(tokens);
					LoginResponseDto loginResponseDto = new LoginResponseDto(new ResponseDto(usersInfo.getId(), usersInfo.getFirstName(), usersInfo.getLastName(), usersInfo.getEmail(), usersInfo.getLastName()+usersInfo.getFirstName()), jwt, refreshJwt);
					return ResponseEntity.ok(loginResponseDto);
				}		
			}	
				return ResponseEntity.ok(new Error("thong tin dang nhap khong chinh xac"));

	}
	
	@GetMapping("/logout")
	public String logout(@RequestParam String jwt) {
		
	    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
	    	  Long userId = tokenProvider.getUserIdFromJWT(jwt);
	    	  System.out.println(userId);
	    	  tokensService.deleteByUserId(Integer.parseInt(userId.toString()));
	    	  return "da logout";
	    }
		
	    return "token khong chinh xac";
		
	}
	
}
