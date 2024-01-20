package hellojpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);   // **
            em.persist(member);

//            team.getMembers().add(member); // ** 양방향 값 셋팅이 필요하다.

            em.flush();
            em.clear();

            //조회
            Team findTeam = em.find(Team.class, team.getId());
            int memberSize = findTeam.getMembers().size();  // 역방향 조회

            System.out.println("memberSize = " + memberSize);
            //


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
