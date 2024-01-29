package com.sj.SecurityEx;

import com.sj.SecurityEx.dto.MemberDto;
import com.sj.SecurityEx.entity.Member;
import com.sj.SecurityEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("MemberDetailsService loadUserByUsername  " + username);

        Optional<Member> result = memberRepository.findByEmail(username, false);

        System.out.println("result = " + result);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check Email or Social");
        }

        Member member = result.get();

        log.info("-------------------------------------");
        log.info(member);

        MemberDto memberDto = new MemberDto(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
                );

        memberDto.setName(member.getName());
        memberDto.setFromSocial(member.isFromSocial());

        return memberDto;
    }
}
