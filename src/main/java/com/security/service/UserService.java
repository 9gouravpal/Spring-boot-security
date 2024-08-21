package com.security.service;

import com.security.entity.UserInfo;
import com.security.repository.UserReposiory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class UserService {
    private final UserReposiory reposiory;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserReposiory reposiory, PasswordEncoder passwordEncoder) {
        this.reposiory = reposiory;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(UserInfo user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        reposiory.saveAndFlush(user);
        return "saved sucessfully!";
    }
    public List<UserInfo> getAllUser(){
        List<UserInfo> userlist=new ArrayList<>();
        userlist=reposiory.findAll();
        return userlist;
    }
    public UserInfo getUserDetailById(Long userid){
        Optional<UserInfo> user=reposiory.findById(userid);
        if (user.isPresent()){
            UserInfo userInfo=user.get();
            return userInfo;
        }
        else {
           log.error("user not found with id"+userid);
        }
        return  null;
    }
}
