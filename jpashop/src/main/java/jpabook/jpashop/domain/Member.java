package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    // 실무에서 MEMBER-ORDER 양방향관계는 비지니스적으로 바람직한 로직이 아니다.
    @OneToMany(mappedBy = "member")
    private List<Order> Orders = new ArrayList<>();
}
