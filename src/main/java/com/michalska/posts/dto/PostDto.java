package com.michalska.posts.dto;


import com.michalska.posts.domain.Post;
import lombok.Data;

@Data
public class PostDto {
    private int id;
    private String title;
    private String body;

    public static PostDto from(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());
        return postDto;

    }
}
