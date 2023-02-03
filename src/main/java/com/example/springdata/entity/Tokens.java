package com.example.springdata.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tokens1")
public class Tokens {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private Users userId;
	@Column(length = 250)
	private String refreshToken;
	private String expiresIn;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
	
	public Tokens() {
		super();
	}
	public Tokens( Users userId, String refreshToken, String expiresIn, LocalDateTime updatedAt,
			LocalDateTime createdAt) {
		super();
		this.userId = userId;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
