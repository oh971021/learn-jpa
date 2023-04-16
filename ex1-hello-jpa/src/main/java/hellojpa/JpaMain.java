package hellojpa;

import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // Application Loading 시점에 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 멤버 저장
            Member member = new Member();
            member.setUsername("member1");
            // JPA가 멤버 필드의 객체를 PK값으로 불러온다.
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // 필요 필드의 값을 불러올 수 있다.
                // 셀렉트 쿼리가 안나오는 이유는 영속성 컨텍스트에 있는 데이터로 조회하기 때문이다.
                    // 쿼리를 보고 싶으면 영속성 컨텍스트를 실행 후 초기화한다. (flush() -> clear())
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("findTeam = " + findTeam.getName());

            // 새로운 팀으로 변경하려면 기존 데이터를 불러와서 사용한다.
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
