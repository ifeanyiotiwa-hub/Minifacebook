package dev.decagon.facebookclone.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    private User user;

    @OneToMany
    private Collection<Comment> listOfComments = new LinkedList<>();

    @OneToMany
    private List<PostLikes> postLikes = new LinkedList<>();


}
