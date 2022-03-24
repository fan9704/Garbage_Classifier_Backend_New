package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(){

    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User getUser(Long id){
        return  userRepository.findOne(id);
    }
    public void updateUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.delete(id);
    }
    public Page<User> getAllUsers(Integer page, Integer size) {
        Page<User> pageUsers;
        pageUsers = userRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending())););
        return pageUsers;
    }
}
