package com.ticket.user.controller;

import com.ticket.api.ApiResponse;
import com.ticket.token.domain.Token;
import com.ticket.user.dto.MemberDto;
import com.ticket.user.dto.MemberLoginDto;
import com.ticket.user.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService userService;

    public MemberController(final MemberService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("/join")
    public ApiResponse join(@RequestBody final MemberDto userDto) {
        userService.join(userDto.getUserId(), userDto.getUserPw());
        return new ApiResponse<>(HttpStatus.OK,"success",null);
    }

    @PostMapping
    @RequestMapping("/login")
    public ApiResponse login(@RequestBody final MemberLoginDto userLoginDto) {
        Token token = userService.login(userLoginDto.getUserId(), userLoginDto.getUserPw());
        return new ApiResponse<>(HttpStatus.OK,"success", token.getToken());
    }
}
