package com.michalska.posts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michalska.posts.domain.Post;
import com.michalska.posts.exception.PostNotFoundException;
import com.michalska.posts.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.michalska.posts.util.JsonReader.readJsonFromUrl;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public static void readJson(Service service) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = String.valueOf(readJsonFromUrl("http://jsonplaceholder.typicode.com/posts"));

        try {
            Post[] p1 = objectMapper.readValue(json, Post[].class);

            System.out.println("Json array to object");
            for (Post post : p1) {
                service.createPost(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Post deletePost(int id) {
        Post post = getPostById(id);
        repository.delete(post);
        return post;
    }

    public List<Post> filter(String keyword) {
        return repository.filter("%" + keyword + "%");
    }

    public boolean validate(Post post) {
        return post.getTitle() != null && !post.getTitle().isEmpty()
                && post.getBody() != null && !post.getBody().isEmpty();
    }
}