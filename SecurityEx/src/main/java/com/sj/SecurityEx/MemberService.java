package com.sj.SecurityEx;

import com.sj.SecurityEx.dto.MemberDto;
import com.sj.SecurityEx.entity.Member;
import com.sj.SecurityEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String save(MemberDto memberDto) throws Exception {


        return memberRepository.save(Member.builder()
                .email(memberDto.getEmail())
                .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                .fromSocial(memberDto.isFromSocial())
                .build()).getEmail();
    }
}
