package hellojpa;
// 객체입장에서 양방향 관계를 만들면 고민거리만 많이 늘어난다.
// 설계측면에서 단방향 관계만으로 해도 충분하며 기본적으로 단방향 설계로 하고 추후 필요에 따라 양방향을 써도 상관없다.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name= "USERNAME")
    private String username;

    @ManyToOne  // 팀과 1:N 관계, 외래키가 있는 곳이 연관관계의 주인
    @JoinColumn(name = "TEAM_ID") // 팀의 PK
    private Team team;

    // 연관관계 편의 메서드
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

}
