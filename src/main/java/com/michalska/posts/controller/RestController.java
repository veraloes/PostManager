package com.michalska.posts.controller;

import com.michalska.posts.domain.Post;
import com.michalska.posts.dto.PostDto;
import com.michalska.posts.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping
public class RestController {
    private final Service service;
    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public RestController(Service service) {
        this.service = service;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException {
        Service.readJson(service);
        System.out.println("Data loaded.");
    }

    @GetMapping("/getAll")
    @Scheduled(cron = "0 0 12 1/1 * ? *")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<Post> posts = service.getAll();
        List<PostDto> postsDto = posts.stream().map(PostDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(postsDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") int id) {
        Post post = service.getPostById(id);
        return new ResponseEntity<>(PostDto.from(post), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable("id") int id) {
        Post post = service.deletePost(id);
        return new ResponseEntity<>(PostDto.from(post), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> editPost(@PathVariable int id, @RequestBody PostDto postDto) {
        Post post = service.getPostById(id);
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        final Post editedPost = service.update(post);
        return ResponseEntity.ok(editedPost);
    }

    @GetMapping("/filter/{keyword}")
    public ResponseEntity<List<PostDto>> filter(@PathVariable String keyword) {
        List<Post> filteredPosts = service.filter(keyword);
        List<PostDto> postsDto = filteredPosts.stream().map(PostDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(postsDto, HttpStatus.OK);

    }
}