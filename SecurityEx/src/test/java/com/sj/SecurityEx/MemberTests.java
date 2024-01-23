package com.sj.SecurityEx;

import com.sj.SecurityEx.entity.Member;
import com.sj.SecurityEx.entity.MemberRole;
import com.sj.SecurityEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("더미 사용자 생성")
    public void insertDummies() {

        //1 -80까지는 USER만 지정
        //81- 90까지는 USER, MANAGER
        //91- 100까지는 USER, MANAGER,ADMIN

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@zerock.org")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(bCryptPasswordEncoder.encode("1111"))
                    .build();

            //default role
            member.addMemberRole(MemberRole.USER);
            if(i > 80) {
                member.addMemberRole(MemberRole.MANAGER);
            }
            if(i > 90){
                member.addMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.findByEmail("user95@zerock.org", false);

        Member member = result.get();

        System.out.println("member = " + member);
    }

}
