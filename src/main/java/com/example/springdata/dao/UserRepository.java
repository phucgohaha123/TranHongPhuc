package com.example.springdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdata.entity.Users;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Integer> {
	@Query(value = "select count(*) from users1 u where u.email = ?1", nativeQuery = true)
	public int checkExsistEmail(String email); 
	
	@Query(value = "select id from users1 u order by u.id desc LIMIT 1", nativeQuery = true)
	public int getLastIdUserInsert();
	
	@Query(value = "select * from users1 u where u.email = ?1 LIMIT 1", nativeQuery = true)
	public Users getUserByEmail(String email); 
}
