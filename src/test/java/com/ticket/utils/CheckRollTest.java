package com.ticket.utils;

import com.ticket.user.domain.Member;
import com.ticket.user.repository.MemberRepository;
import com.ticket.user.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CheckRollTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CheckRoll checkRoll;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    @DisplayName("유저의 Roll이 Admin인지 체크")
    void checkRollAdmin() {
        // given
        final String userId = "test";
        final String userPw = "1234";

        memberService.join(userId, userPw);
        Optional<Member> byUserId = memberRepository.findByUserId(userId);

        // when & then
    }
}