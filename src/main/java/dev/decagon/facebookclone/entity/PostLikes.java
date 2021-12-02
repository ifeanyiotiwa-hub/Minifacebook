package dev.decagon.facebookclone.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "likes")
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;


    @ManyToOne
    private Post post;


    @ManyToOne
    private User user;

    public PostLikes() {
    }
}
