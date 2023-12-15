package com.socialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.socialMedia.modelEntity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	//method for Finding Posts By User ID
	@Query("SELECT p FROM Post p WHERE p.user.id =:userId")
	List<Post> findPostByUserId(Integer userId);
	
}
