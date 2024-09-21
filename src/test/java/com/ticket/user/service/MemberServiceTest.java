package com.ticket.user.service;

import com.ticket.token.domain.Token;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.repository.MemberRepository;
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
class MemberServiceTest {

    @Autowired
    private MemberService userService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private TokenRepository tokenRepository;

    @Test
    @DisplayName("회원가입 성공")
    public void join() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        // when
        Member join = userService.join(userId, userPw);

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
        when(memberRepository.findByUserId(userId)).thenReturn(Optional.of(new Member(userId, userPw)));

        // when & then
        assertThrows(RuntimeException.class, () -> userService.join(userId, userPw));
    }

    @Test
    @DisplayName("로그인 성공")
    public void login_success() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        when(memberRepository.findByUserId(userId)).thenReturn(Optional.of(new Member(userId, userPw)));

        // when
        Token token = userService.login(userId, userPw);

        // then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("로그인 실패 - 존재하지 않는 ID")
    public void login_fail() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        // 옵셔널이 빈값이라면 유저가 없는 상황
        when(memberRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> userService.login(userId, userPw));
    }

    @Test
    @DisplayName("로그인 실패 - 틀린 PW")
    public void login_fail_wrong_pw() {
        // given
        final String userId = "test";
        final String userPw = "1234";
        final String wrongPw = "wrongPw";

        // 유저 id가 존재함
        when(memberRepository.findByUserId(userId)).thenReturn(Optional.of(new Member(userId, userPw)));

        // 로그인이 실패 했을때 Exception
        assertThrows(RuntimeException.class, () -> userService.login(userId, wrongPw));
    }

    @Test
    @DisplayName("로그인 성공 - 기존의 토큰을 반환하는지 테스트")
    public void login_success_already_exists_token() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        when(memberRepository.findByUserId(userId)).thenReturn(Optional.of(new Member(userId, userPw)));

        // when
        Token token =  userService.login(userId, userPw);
        Token alreadyExistToken = userService.login(userId, userPw);

        // then
        assertThat(token.getToken()).isEqualTo(alreadyExistToken.getToken());
    }
}
