package com.michalska.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
    public List<Post> filter(@Param("keyword") String keyword);
}
