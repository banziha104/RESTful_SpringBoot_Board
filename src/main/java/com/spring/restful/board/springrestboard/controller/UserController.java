package com.spring.restful.board.springrestboard.controller;

import com.spring.restful.board.springrestboard.domain.User;
import com.spring.restful.board.springrestboard.exception.AlreadyExistsException;
import com.spring.restful.board.springrestboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/join")
    public User create(@RequestBody User user){
        try{
            System.out.println(user);
            return userService.join(user.getName(),user.getPassword());
        }catch (AlreadyExistsException e){
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping(value = "/login")
    public User login(@RequestParam("name") String name, @RequestParam("password") String password){ // 쿼리받기
        System.out.println("get : username"+name+" / password"+password);
        return userService.authentication(name,password);
    }
}
