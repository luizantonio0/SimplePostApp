package com.agora.SimplePostApp.controller;

import com.agora.SimplePostApp.dto.AuthDTO;
import com.agora.SimplePostApp.dto.LoginReponseDTO;
import com.agora.SimplePostApp.dto.RegisterDTO;
import com.agora.SimplePostApp.models.User;
import com.agora.SimplePostApp.repository.UserRepository;
import com.agora.SimplePostApp.service.TokenService;
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
    final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager,  UserRepository userRepository,  TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data){
        var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken( (User) auth.getPrincipal());

        return new ResponseEntity<>(new LoginReponseDTO(token), HttpStatus.OK);
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
