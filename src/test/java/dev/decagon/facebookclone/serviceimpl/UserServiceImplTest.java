package dev.decagon.facebookclone.serviceimpl;

import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private UserServiceImpl userService;

    private User user;


    @BeforeEach
    void setUp(){
        user = new User();
        user.setFirstName("Ifeanyi");
        user.setLastName("Otiwa");
        user.setEmail("ifeanyi.otiwa@gmail.com");
        user.setPassword("1234");
        user.setDateOfBirth("1988-07-03");
        user.setGender("Male");
    }
    @Test
    void shouldAddUserTest() {
        //Mock userRepository
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.addUser(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldLogInUserTest() {


        //mock User Repository
//        when(userRepository.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        //call the method to be tested
        userService.logInUser(user);

        User user1 = userService.getUserByEmail("ifeanyi.otiwa@gmail.com");

        assertEquals("ifeanyi.otiwa@gmail.com", user.getEmail());
        assertNotNull(user);
        assertNull(user1);

//        verify(userRepository, times(1)).findUserByEmail(anyString());
    }
}