package com.agora.SimplePostApp.models;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "tb_user")
    private final User owner;
    private Integer likes;

    public Post(Long id, String content, String question, User owner) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.owner = owner;
    }

    public Post() {
        this.owner = null;
    }

}
