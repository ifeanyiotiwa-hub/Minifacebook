package dev.decagon.facebookclone;

import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.repository.CommentRepository;
import dev.decagon.facebookclone.repository.PostRepository;
import dev.decagon.facebookclone.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class Week7TaskIfeanyiotiwaHubApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    User testUser = new User();


    @Test
    void contextLoads() {

        testUser.setFirstName("Chika");
        testUser.setLastName("Nwobi");
        testUser.setEmail("chika.nwobi@gmail.com");
        testUser.setDateOfBirth("1982-01-01");
        testUser.setGender("male");
        testUser.setPassword("1234");
    }

    @Test
    public void testCreate(){


        userRepository.save(testUser);

        assertNotNull(userRepository.getUserByEmail(testUser.getEmail()));
        assertEquals("1234", userRepository.getUserByEmail(testUser.getEmail()).get().getPassword());

    }

    @Test
    public void testRead(){
        User user = userRepository.findById(testUser.getUserId()).get();
        assertNotNull(user);
        assertEquals("Chika", user.getFirstName());
    }


    @Test
    public void testDelete(){
        var tempUser = userRepository.findUserByEmail("chika.nwobi@gmail.com").get();
        userRepository.deleteById(tempUser.getUserId());
    }

}
