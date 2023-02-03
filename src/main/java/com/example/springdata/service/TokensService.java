package com.example.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springdata.dao.TokensRepository;
import com.example.springdata.entity.Tokens;

@Service
@Transactional
public class TokensService {
	@Autowired 
	private TokensRepository tokensRepository;  
	public void save(Tokens tokens) {
		tokensRepository.save(tokens);
	}
	
	public void deleteByUserId(int userId) {
		 tokensRepository.deleteAll(tokensRepository.getTokensByUserId(userId));
	}
}
