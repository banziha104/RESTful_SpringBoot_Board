package com.spring.restful.board.springrestboard.repository;

import com.spring.restful.board.springrestboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    User findByNameAndPassword(String name, String password);
}
