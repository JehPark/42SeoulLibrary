package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByNickNameLike(String name);
    User findUserByNickName(String name);
}
