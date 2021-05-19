package com.michalska.posts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michalska.posts.domain.Post;
import com.michalska.posts.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

import static com.michalska.posts.util.JsonReader.readJsonFromUrl;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {
    private final Service service;
    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping
    public String showHome(Model model) {
        return "home";
    }

    @GetMapping(value = "/update")
    @Scheduled(cron = "0 0 12 1/1 * ? *")
    public String updateData(Model model) throws IOException {
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
        model.addAttribute("size", service.getAll().size());
        return "posts";
    }


    @GetMapping(value = {"/list"})
    public String showList(Model model, @Param("keyword") String keyword) {
        model.addAttribute("standardDate", new Date());

        if (keyword != null) {
            model.addAttribute("keyword", keyword);
            model.addAttribute("post", service.filter(keyword));
        } else {
            model.addAttribute("post", service.getAll());
        }
        return "list";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String editPost(@PathVariable("id") int id, Model model) {
        model.addAttribute("editPost", service.getPostById(id));
        return "post-edit-form";
    }

    @PostMapping(value = {"/edit/{id}"})
    public String updatePost(@ModelAttribute("editPost") Post post, Model model) {
        if (this.service.validate(post)) {
            model.addAttribute("post", service.getAll());
            service.update(post);
            return "list";
        } else {
            model.addAttribute("errorMessage", errorMessage);
            return "post-edit-form";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String deletePost(@PathVariable("id") int id, Model model) {
        service.delete(id);
        model.addAttribute("post", service.getAll());
        return "list";
    }

}
