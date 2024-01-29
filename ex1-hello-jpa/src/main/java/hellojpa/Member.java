package hellojpa;
// 객체입장에서 양방향 관계를 만들면 고민거리만 많이 늘어난다.
// 설계측면에서 단방향 관계만으로 해도 충분하며 기본적으로 단방향 설계로 하고 추후 필요에 따라 양방향을 써도 상관없다.

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @ManyToOne(fetch = FetchType.LAZY) // 프록시 객체로 조회
    @ManyToOne(fetch = FetchType.EAGER) // 실무에선 가급적 지연 로딩만 사용!!
                                        // 1. 즉시 로딩을 적용하면 예상하지 못한 SQL이 발생
    //                                     2.즉시 로딩은 JPQL에서 N+1 문제를 일으킨다.
    @JoinColumn
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProductLists = new ArrayList<>();


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<MemberProduct> getMemberProductLists() {
        return memberProductLists;
    }

    public void setMemberProductLists(List<MemberProduct> memberProductLists) {
        this.memberProductLists = memberProductLists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
