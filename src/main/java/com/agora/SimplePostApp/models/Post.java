package com.agora.SimplePostApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String question;
    private final User owner;
    private Integer likes;

    public Post(User owner) {
        this.owner = owner;
    }

    public Post(Long id, String content, String question, User owner) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.owner = owner;
    }
}
