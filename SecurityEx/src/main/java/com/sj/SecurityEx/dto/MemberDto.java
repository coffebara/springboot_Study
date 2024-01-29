package com.sj.SecurityEx.dto;

import com.sj.SecurityEx.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

// DTO 역할을 수행하는 클래스인 동시에 스프링 시큐리티에서 인가/인증 작업에 사용할 수 있음
@Getter @Setter
public class MemberDto extends User {
    private String email;
    private String name;
    private boolean fromSocial;

    public MemberDto(String username, String password, boolean fromSocial,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }




//    public MemberDto(String username, String password){
//        this.username = username;
//        this.password = password;
//    }

//    public static MemberDto toMember(Member member){
//        return new MemberDto(
//                member.getUsername(),
//                member.getPassword()
//        );
//    }
}
