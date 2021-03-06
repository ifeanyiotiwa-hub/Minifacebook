package dev.decagon.facebookclone.repository;


import dev.decagon.facebookclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByEmailAndPassword(String email, String password);

   Optional<User> findUserByEmail(String anyString);
}
