package dev.decagon.facebookclone.repository;

import dev.decagon.facebookclone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Is Not Null and Order by Post Id Desc
    List<Post> findAllByPostIdIsNotNullOrderByPostIdDesc(); //
    Post findByPostId(Long Id);
}
