package com.ticket.user.service;

import com.ticket.user.domain.User;
import com.ticket.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(final String userId, final String userPw) {

        // 회원가입 전 id 중복검사
        Optional<User> existUser = userRepository.findByUserId(userId);

        // 중복시 예외 처리
        if (existUser.isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자 ID입니다.");
        }

        User user = new User(userId, userPw);
        userRepository.save(user);

        return user;
    }

    public void login(final String userId, final String userPw) {
        userRepository.findByUserId(userId);
    }
}
