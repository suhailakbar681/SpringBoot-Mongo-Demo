package com.example.poc.demo.poc.service;

import com.example.poc.demo.poc.common.Messages;
import com.example.poc.demo.poc.common.dto.response.RestResponse;
import com.example.poc.demo.poc.mapper.UserMapper;
import com.example.poc.demo.poc.model.User;
import com.example.poc.demo.poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public RestResponse saveUser(User user){
        try{
            if(!isEmailExist(user.getEmail())){
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());
                userRepository.insert(user);
                return RestResponse.of(user,Messages.USER_CREATE_SUCCESS);
            }
            return RestResponse.fail(Messages.USER_ALREADY_EXIST);
        }
        catch (Exception ex){
            return RestResponse.fail(Messages.USER_CREATE_FAIL);
        }
    }

    public Boolean isEmailExist(String email){
        if(userRepository.findByEmail(email) == null){
            return false;
        }
        return true;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public RestResponse updateUser(User updateUser){
        try{
            User existingUser = findByEmail(updateUser.getEmail());
            if(existingUser != null){

                existingUser = UserMapper.mapUserToUpdateUser(existingUser,updateUser);
                userRepository.save(existingUser);
                return RestResponse.of(existingUser,Messages.USER_UPDATED);
            }
            return RestResponse.fail(Messages.USER_NOT_FOUND);
        }
        catch (Exception ex){
            return RestResponse.fail(Messages.USER_UPDATE_FAIL);
        }
    }

    public RestResponse deleteUserByEmail(String email){
        if(!isEmailExist(email)){
            return RestResponse.fail(Messages.USER_NOT_FOUND);
        }
        if(userRepository.deleteUserByEmail(email) != null){
            return RestResponse.success(Messages.USER_DELETED);
        }
        return RestResponse.fail(Messages.USER_DELETE_FAIL);
    }

    public RestResponse deleteAllUsers(){
        userRepository.deleteAll();
        return RestResponse.success(Messages.ALL_USERS_DELETED);
    }

    public RestResponse findUserByEmail(String email){
        try{
            User user = findByEmail(email);
            if(userRepository.findByEmail(email) != null){
                return RestResponse.of(user);
            }
            return RestResponse.fail(Messages.USER_NOT_FOUND);
        }
        catch (Exception ex){
            return RestResponse.fail(Messages.USER_FIND_BY_EMAIL_FAIL);
        }
    }

}
