package com.spring.restful.board.springrestboard.service;

import com.spring.restful.board.springrestboard.domain.User;
import com.spring.restful.board.springrestboard.exception.AlreadyExistsException;
import com.spring.restful.board.springrestboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User join(String username, String password) {
        User user = userRepository.findByName(username);
        if (username != null && password != null) {
            if (user != null) throw new AlreadyExistsException("존재 하는 이름");
            return userRepository.save(new User(username, password));
        } else {
            return null;
        }
    }

    @Override
    public User authentication(String username, String password) {
        User user = userRepository.findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
