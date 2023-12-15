package com.socialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.modelEntity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
