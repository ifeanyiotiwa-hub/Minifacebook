package dev.decagon.facebookclone.service;

import dev.decagon.facebookclone.dto.ResponseDTO;
import dev.decagon.facebookclone.entity.User;

public interface UserService {
    ResponseDTO addUser(User user);
    ResponseDTO logInUser(User user);
}
