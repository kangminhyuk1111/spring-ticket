package com.ticket.user.service;

import com.ticket.token.provider.FakeTokenProvider;
import com.ticket.token.repository.FakeTokenRepository;
import com.ticket.token.domain.Token;
import com.ticket.token.provider.TokenProvider;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.repository.FakeMemberRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.domain.role.MemberRole;
import com.ticket.user.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    private MemberRepository memberRepository = new FakeMemberRepository();
    private TokenRepository tokenRepository = new FakeTokenRepository();
    private TokenProvider tokenProvider = new FakeTokenProvider();

    private MemberService memberService = new MemberService(memberRepository, tokenRepository, tokenProvider);

    @Test
    @DisplayName("회원가입 성공")
    public void join() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        // when
        Member join = memberService.join(userId, userPw);

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
        memberService.join(userId,userPw);

        // when & then
        assertThrows(RuntimeException.class, () -> memberService.join(userId, userPw));
    }

    @Test
    @DisplayName("로그인 성공")
    public void login_success() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        memberService.join(userId,userPw);

        // when
        Token token = memberService.login(userId, userPw);

        // then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("로그인 실패 - 존재하지 않는 ID")
    public void login_fail() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        // when & then
        assertThrows(RuntimeException.class, () -> memberService.login(userId, userPw));
    }

    @Test
    @DisplayName("로그인 실패 - 틀린 PW")
    public void login_fail_wrong_pw() {
        // given
        final String userId = "test";
        final String userPw = "1234";
        final String wrongPw = "wrongPw";

        // 유저 id가 존재함
        memberService.join(userId,userPw);

        // 로그인이 실패 했을때 Exception
        assertThrows(RuntimeException.class, () -> memberService.login(userId, wrongPw));
    }

    @Test
    @DisplayName("로그인 성공 - 기존의 토큰을 반환하는지 테스트")
    public void login_success_already_exists_token() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        memberRepository.save(new Member(userId, userPw));
        tokenRepository.save(tokenProvider.createToken(userId));

        // when
        Token token = memberService.login(userId, userPw);
        Token alreadyExistToken = memberService.login(userId, userPw);

        // then
        assertThat(token.getToken()).isEqualTo(alreadyExistToken.getToken());
    }

    @Test
    @DisplayName("유저의 등급을 일반 사용자에서 관리자 등급으로 업데이트")
    public void change_member_role_to_admin() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        Member member = new Member(userId, userPw);
        memberRepository.save(member);

        // when
        memberService.changeMemberRole(userId, MemberRole.ROLE_ADMIN);

        // then
        assertThat(member.getRole()).isEqualTo(MemberRole.ROLE_ADMIN);
    }

}
