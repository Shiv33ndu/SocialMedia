package com.socialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.socialMedia.modelEntity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{

	public UserInfo findByEmail(String email);
	
	//method to give search results
	@Query("SELECT u from UserInfo u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
	public List<UserInfo> findByQuery(@Param("query") String query);
	
	

}
