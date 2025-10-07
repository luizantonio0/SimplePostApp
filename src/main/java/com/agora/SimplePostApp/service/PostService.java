package com.agora.SimplePostApp.service;

import com.agora.SimplePostApp.models.Post;
import com.agora.SimplePostApp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() { return postRepository.findAll(); }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

    public void like(Long id){
        Post post = postRepository.findById(id).get();
        post.setLikes(post.getLikes()+1);
        postRepository.save(post);
    }

}
