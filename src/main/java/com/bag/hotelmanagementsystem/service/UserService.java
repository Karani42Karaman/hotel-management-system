package com.bag.hotelmanagementsystem.service;

import com.bag.hotelmanagementsystem.model.UserModel;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    List<UserModel> getAllUser();
    UserModel saveUser(UserModel userModel);
    UserModel getUserById(Long id);
    UserModel updateUser(UserModel userModel);
    void deleteUserById(Long id);
    UserModel getUser(String email, Long tcNo);
    List<UserModel> getUserByRole(boolean isAdmin);

}
