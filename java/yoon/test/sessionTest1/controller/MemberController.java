package yoon.test.sessionTest1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoon.test.sessionTest1.config.SessionManager;
import yoon.test.sessionTest1.dto.reponse.MemberResponse;
import yoon.test.sessionTest1.dto.reponse.ResponseMessage;
import yoon.test.sessionTest1.dto.request.MemberLoginDto;
import yoon.test.sessionTest1.dto.request.MemberRegisterDto;
import yoon.test.sessionTest1.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public ResponseEntity<?> getUserInfo(@CookieValue("YSESSIONID") String uuid, HttpServletRequest request, HttpServletResponse response){
        System.out.println(uuid);
        MemberResponse result = sessionManager.getValue(uuid);
        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("유저 정보");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> join(@RequestBody MemberRegisterDto dto){

        MemberResponse result = memberService.join(dto);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("회원가입 성공");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginDto dto, HttpServletRequest request, HttpServletResponse response){

        MemberResponse result = memberService.login(dto);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("로그인 성공");
        message.setData(result);

        String jSessionId = sessionManager.createSession(request, result);

        Cookie cookie = new Cookie("YSESSIONID", jSessionId);
        response.addCookie(cookie);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
