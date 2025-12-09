package com.agora.SimplePostApp.configs;

import com.agora.SimplePostApp.repository.UserRepository;
import com.agora.SimplePostApp.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    final TokenService tokenService;
    final UserRepository repository;

    public SecurityFilter(TokenService tokenService,  UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (!token.isEmpty()) {
            var subject = tokenService.validateToken(token);
            UserDetails user = repository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request){
        var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")){
            return "";
        }
        return header.replace("Bearer ", "");
    }
}
