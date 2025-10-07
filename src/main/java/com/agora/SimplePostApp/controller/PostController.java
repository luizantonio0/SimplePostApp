package com.agora.SimplePostApp.controller;

import com.agora.SimplePostApp.models.Post;
import com.agora.SimplePostApp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll(){
        return postService.getAll();
    }

    @PostMapping
    public Post save(@RequestBody Post post){
        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
    @PatchMapping("/like/{id}")
    public void like(@PathVariable Long id){postService.like(id);}

}
