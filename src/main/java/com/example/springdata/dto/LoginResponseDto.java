package com.example.springdata.dto;

public class LoginResponseDto {
	private ResponseDto userRespone;
	private String token;
	private String refreshToken;
	
	public LoginResponseDto(ResponseDto userRespone, String token, String refreshToken) {
		super();
		this.userRespone = userRespone;
		this.token = token;
		this.refreshToken = refreshToken;
	}
	public ResponseDto getUserRespone() {
		return userRespone;
	}
	public void setUserRespone(ResponseDto userRespone) {
		this.userRespone = userRespone;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


	
}
