package com.michalska.posts.service;

import com.michalska.posts.domain.Post;
import com.michalska.posts.exception.PostNotFoundException;
import com.michalska.posts.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public Post createPost(Post post) {
        return repository.save(post);
    }

    public List<Post> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Post getPostById(int id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post update(Post post) {
        return repository.save(post);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Post> filter(String keyword) {
        return repository.filter("%" + keyword + "%");
    }

    public boolean validate(Post post) {
        return post.getTitle() != null && !post.getTitle().isEmpty()
                && post.getBody() != null && !post.getBody().isEmpty();
    }
}




