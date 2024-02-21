package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

// SpringBootApplication이 하위 어노테이션을 자동으로 검색하여 스프링 Bean으로 등록
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // JPA의 Entity Manager를 자동으로 주입해줌.
//    @PersistenceContext
//    private EntityManager em;
    // 스프링부트 부터 @PersitenceContext가 아닌 @Autowird를 지원해준다 따라서 @RequiredArgsConstructor 가능
    final private EntityManager em;

    public Long join(Member member) {
        em.persist(member); // 영속성 컨텍스트에 member를 넣고 추후 transaction이 commit이 되는 시점에 DB에 저장됨
        return member.getId();
    }

    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    // jpql은 객체를 대상으로 조회함 <-> sql은 테이블 대상
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

