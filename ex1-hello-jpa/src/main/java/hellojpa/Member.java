package hellojpa;
// 객체입장에서 양방향 관계를 만들면 고민거리만 많이 늘어난다.
// 설계측면에서 단방향 관계만으로 해도 충분하며 기본적으로 단방향 설계로 하고 추후 필요에 따라 양방향을 써도 상관없다.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;


@Entity
@Getter
@Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name= "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

}
