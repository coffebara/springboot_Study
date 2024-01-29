package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    // 실무에서 MEMBER-ORDER 양방향관계는 비지니스적으로 바람직한 로직이 아니다.
    @OneToMany(mappedBy = "member")
    private List<Order> Orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Member setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<Order> getOrders() {
        return Orders;
    }

    public Member setOrders(List<Order> orders) {
        Orders = orders;
        return this;
    }
}
