package com.agora.SimplePostApp.service;

import com.agora.SimplePostApp.models.Post;
import com.agora.SimplePostApp.models.User;
import com.agora.SimplePostApp.repository.PostRepository;
import com.agora.SimplePostApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() { return userRepository.findAll(); }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }


}

