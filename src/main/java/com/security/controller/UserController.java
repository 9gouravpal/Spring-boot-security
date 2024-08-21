package com.security.controller;

import com.security.entity.UserInfo;
import com.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private  final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/welcome")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
    @PostMapping("/add")
    public String addNew(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity< List<UserInfo>> getAllUser(){

        try {

            List<UserInfo>  user=service.getAllUser();
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);

        }
        catch (Exception e){
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/userGetById/{userid}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getUserById(@PathVariable Long userid){
        try {
            UserInfo userInfo=service.getUserDetailById(userid);
            return new ResponseEntity<>(userInfo,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
