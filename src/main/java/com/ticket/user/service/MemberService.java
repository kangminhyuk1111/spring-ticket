package com.ticket.user.service;

import com.ticket.exception.member.AlreadyExistMemberException;
import com.ticket.exception.member.NotFoundMemberException;
import com.ticket.exception.member.WrongPasswordException;
import com.ticket.token.domain.Token;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.domain.role.MemberRole;
import com.ticket.user.repository.MemberRepository;
import com.ticket.token.provider.TokenProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;

    public MemberService(MemberRepository memberRepository, TokenRepository tokenRepository, TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
        this.tokenProvider = tokenProvider;
    }

    public Member join(final String userId, final String userPw) {

        // 회원가입 전 id 중복검사
        Optional<Member> existUser = memberRepository.findByUserId(userId);

        // 중복시 예외 처리
        if (existUser.isPresent()) {
            throw new AlreadyExistMemberException();
        }

        Member member = new Member(userId, userPw);

        memberRepository.save(member);

        return member;
    }

    public Token login(final String userId, final String userPw) {

        // 아이디 존재하는지 검사
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(NotFoundMemberException::new);

        // 비밀번호 맞는지 검사 -> 아니라면 에러 발생
        if (!member.checkPassword(userPw)) {
            throw new WrongPasswordException();
        }

        // 이미 발급된 토큰 조회
        Optional<Token> existingToken = tokenRepository.findByUserId(userId);

        // 기존 토큰이 존재하면 반환
        if (existingToken.isPresent()) {
            return existingToken.get();
        }

        // 기존 토큰이 존재하지 않을시, 유저에게 토큰 발급
        Token token = tokenProvider.createToken(userId);

        // 토큰을 서버에서 보관함
        tokenRepository.save(token);

        return token;
    }


    // 유저의 등급을 어드민으로 올림
    public Member changeMemberRole(final String userId, final MemberRole role) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(NotFoundMemberException::new);

        member.setRole(role);

        memberRepository.save(member);

        return member;
    }
}
