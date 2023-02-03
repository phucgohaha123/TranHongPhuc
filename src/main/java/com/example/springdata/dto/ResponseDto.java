package com.example.springdata.dto;

public class ResponseDto {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String displayName;
	
	public ResponseDto(int id, String firstName, String lastName, String email, String displayName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.displayName = displayName;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public static ResponseDto convertFromRequestDto(UserRequestDto userRequestDto, int id) {
		ResponseDto responseDto = new ResponseDto
				(id, userRequestDto.getFirstName(), userRequestDto.getLastName(),
				userRequestDto.getEmail(), userRequestDto.getFirstName()+userRequestDto.getLastName());
		return responseDto;
		
	}

}
