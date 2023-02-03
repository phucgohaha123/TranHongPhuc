package com.example.springdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdata.dao.UserRepository;
import com.example.springdata.entity.Users;

@Service
@Transactional
public class UsersService {
	@Autowired 
	private UserRepository userRepository;  
	public List<Users> geUsers() {
		return (List<Users>) userRepository.findAll();
	}
	
	public int checkExsistEmail(String email) {
		return userRepository.checkExsistEmail(email);
	}
	
	public void save(Users users) {
		userRepository.save(users);
	}
	
	public int getLastUserIdInsert() {
		return userRepository.getLastIdUserInsert();
	}
	
	public Users getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
	
	public Users getUsersById(int id) {
		return userRepository.getOne(id);
	}
}
