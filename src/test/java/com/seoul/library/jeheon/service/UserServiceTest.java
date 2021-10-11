package com.seoul.library.jeheon.service;

import com.seoul.library.jeheon.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void 같은이름을가지면안됨() throws Exception{
        //given
        final User user1 = User.builder()
                .intraId("jehpark")
                .nickName("wpgjs1230")
                .build();
        final User user2 = User.builder()
                .intraId("jehpark")
                .nickName("wpgjs1230")
                .build();
        //when
        userService.saveUser(user1);
        //then
        final IllegalStateException exception = assertThrows(
                IllegalStateException.class, () -> userService.saveUser(user2));
        assertThat(exception.getMessage()).isEqualTo("이미 같은 이름을 가진 유저가 존재합니다.");
    }
}