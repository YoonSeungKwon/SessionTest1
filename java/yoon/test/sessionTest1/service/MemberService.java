package yoon.test.sessionTest1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.test.sessionTest1.domain.Members;
import yoon.test.sessionTest1.dto.reponse.MemberResponse;
import yoon.test.sessionTest1.dto.request.MemberLoginDto;
import yoon.test.sessionTest1.dto.request.MemberRegisterDto;
import yoon.test.sessionTest1.enums.Role;
import yoon.test.sessionTest1.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private MemberResponse toResponse(Members member){
        return new MemberResponse(member.getEmail(), member.getName(), member.getRoleKey(), member.getRegdate());
    }

    public MemberResponse join(MemberRegisterDto dto){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Members member = Members.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .role(Role.USER)
                .build();

        return toResponse(memberRepository.save(member));
    }

    public MemberResponse login(MemberLoginDto dto)throws UsernameNotFoundException, BadCredentialsException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username = dto.getEmail();
        String password = dto.getPassword();
        Members member = memberRepository.findMembersByEmail(username);

        if(member == null)
            throw new UsernameNotFoundException(username);
        if(!passwordEncoder.matches(password, member.getPassword()))
            throw new BadCredentialsException(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return toResponse(member);
    }


}
