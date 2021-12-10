package dev.decagon.facebookclone.serviceimpl;


import dev.decagon.facebookclone.dto.ResponseDTO;
import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.repository.UserRepository;
import dev.decagon.facebookclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Register user and save details
    @Override
    public ResponseDTO addUser(User user) {
        var userDetails = userRepository.getUserByEmail(user.getEmail());
        ResponseDTO response = new ResponseDTO();

        try{
            if(userDetails.isPresent()){
                throw new Exception("This email is already Registered");
            }
            var savedUser = userRepository.save(user);
            response.setData(savedUser);
            response.setMessage("Registration Successful");
            response.setStatus(true);
            return response;
        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setStatus(false);
            return response;
        }
    }


    // Log in User
    @Override
    public ResponseDTO logInUser(User user) {
        var userDetails = userRepository.getUserByEmailAndPassword(user.getEmail(), user.getPassword());

        ResponseDTO response = new ResponseDTO();

        if(userDetails.isPresent()){
            response.setStatus(true);
            response.setData(userDetails.get());
            response.setMessage("Logged In Successfully");
            return response;
        }

        response.setMessage("Invalid Email or Password");
        return response;
    }

    @Override
    public User getUserByEmail(String anyString) {
        var user = userRepository.findUserByEmail(anyString);
        return user.orElse(null);
    }


}
