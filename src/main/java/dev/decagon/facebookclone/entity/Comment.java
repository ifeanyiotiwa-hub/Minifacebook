package dev.decagon.facebookclone.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String commentBody;

    private Post post;

    private User user;

    private String comments;


    public Comment() {
    }

    public Comment(String commentBody, Post post, User user, String comments) {
        this.commentBody = commentBody;
        this.post = post;
        this.user = user;
        this.comments = comments;
    }

    public Comment(Post post, User user, String comments) {
        this.post = post;
        this.user = user;
        this.comments = comments;
    }

    public Comment(Long commentId, String commentBody) {
        this.commentId = commentId;
        this.commentBody = commentBody;
    }
}
