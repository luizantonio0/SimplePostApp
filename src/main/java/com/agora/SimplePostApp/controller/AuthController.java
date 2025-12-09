package com.agora.SimplePostApp.controller;

import com.agora.SimplePostApp.dto.AuthDTO;
import com.agora.SimplePostApp.dto.RegisterDTO;
import com.agora.SimplePostApp.models.User;
import com.agora.SimplePostApp.repository.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,  UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data){
        var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        if (this.userRepository.findByEmail(data.email()) != null) return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.CONFLICT);

        String password = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), password, data.role());

        userRepository.save(newUser);

        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.CREATED);
    }
}
