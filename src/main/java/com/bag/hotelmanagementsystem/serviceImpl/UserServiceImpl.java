package com.bag.hotelmanagementsystem.serviceImpl;

import com.bag.hotelmanagementsystem.model.UserModel;
import com.bag.hotelmanagementsystem.repository.UserRepository;
import com.bag.hotelmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUser(String email, Long tcNo) {
        return userRepository.findByUsername(email, tcNo);
    }
}
