package dev.decagon.facebookclone.entity;

import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentBody;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    private String comments;


    public Comment(String comments, Post post, User user) {
        this.post = post;
        this.user = user;
        this.commentBody = comments;
    }

    public Comment(Long commentId, String comment) {
        this.commentId = commentId;
        this.commentBody = comment;
    }
}
