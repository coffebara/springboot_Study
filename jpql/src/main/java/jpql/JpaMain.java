package jpql;


import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{


            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select size(t.members) from Team t";
            List<Integer> resultList = em.createQuery(query, Integer.class)
                    .getResultList();
            for (Integer s  : resultList){
                System.out.println("s = " + s);
            }


            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
// JPQL
// SQL이 DB를 다루는 것과 달리 JPQL은 객체를 대상으로 함.
// String(문자)로 작성하기 때문에 동적쿼리를 짜는 것이 어렵고, 컴파일 단계에서 오류를 잡기 어려움

// Criteria
// String(문자)가 아닌 자바코드로 JPQL을 작성하여 동적 쿼리에 유리함
// SQL스럽지 않기때문에 복잡하고 유지보수가 힘듬, 실무 X

//QueryDSL
//자바코드로 JPQL을 작성할 수 있음
//동적쿼리 작성 편함
// 단순하고 쉬워 실무 사용 권장

//flush -> commit, query