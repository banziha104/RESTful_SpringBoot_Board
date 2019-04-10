package com.spring.restful.board.springrestboard.service;

import com.spring.restful.board.springrestboard.domain.User;

public interface UserService {
    User join(String name, String password);
    User authentication(String name, String password);
}
