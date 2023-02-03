package com.example.springdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdata.entity.Tokens;
import com.example.springdata.entity.Users;

@Repository
@Transactional
public interface TokensRepository extends JpaRepository<Tokens, Integer>{

	@Query(value = "select * from tokens1 t where t.user_id = ?1", nativeQuery = true)
	public List<Tokens>getTokensByUserId(int userId); 
}
