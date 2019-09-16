package com.social.pixogram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.social.pixogram.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT * FROM user  WHERE user.name LIKE %?% ", nativeQuery = true)
	public List<User> getUserByUserName(String name);

	
	

}
