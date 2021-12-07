package dev.decagon.facebookclone.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Comment> listOfComments = new ArrayList<>();

    @OneToMany
    private List<PostLikes> postLikes = new ArrayList<>();


}
