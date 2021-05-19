package com.michalska.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServiceTest {

    @Autowired
    private Repository repository;

    @Test
    void getPost() {
        Service service = new Service(repository);
        Post post = new Post(1, 1, "qui est esse", "est rerum tempore vitae sequi sint nihil reprehenderit dolor beatae");
        repository.save(post);
        Post firstPost = service.getPostById(1);

        assertEquals(post.getTitle(), firstPost.getTitle());
        assertEquals(post.getBody(), firstPost.getBody());
        assertEquals(post.getId(), firstPost.getId());
    }
}