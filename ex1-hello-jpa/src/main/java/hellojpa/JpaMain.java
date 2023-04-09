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
            // 영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA"); // Dirty Checking

            em.clear(); // 1차 캐시, 쓰기 지연 SQL 저장소 초기화 및 영속 컨텍스트 제외

            Member member2 = em.find(Member.class, 150L);

            System.out.println("==========");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
