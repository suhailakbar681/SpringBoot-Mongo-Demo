package com.example.poc.demo.poc.mapper;

import com.example.poc.demo.poc.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserMapper {

    public static User mapUserToUpdateUser(User existingUser, User updateUser){
        existingUser.setFirstName(updateUser.getFirstName());
        existingUser.setLastName(updateUser.getLastName());
        existingUser.setGender(updateUser.getGender());
        existingUser.setAddress(updateUser.getAddress());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return existingUser;
    }
}
