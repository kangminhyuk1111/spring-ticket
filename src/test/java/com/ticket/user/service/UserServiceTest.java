package com.ticket.user.service;

import com.ticket.user.domain.User;
import com.ticket.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공")
    public void join() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        // when
        User join = userService.join(userId, userPw);

        // then
        assertThat(join).isNotNull();
    }

    @Test
    @DisplayName("회원가입 실패 - 중복된 아이디가 존재")
    public void join_fail() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        // 기존 유저를 먼저 저장하여 중복 상태로 만듬
        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new User(userId, userPw)));

        // when & then
        assertThrows(RuntimeException.class, () -> userService.join(userId, userPw));
    }
}
