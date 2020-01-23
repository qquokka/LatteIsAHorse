package com.latte.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latte.model.Post;



@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
