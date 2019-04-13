package JPA_BASIC_00;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 엔티티 매니저는 생성 비용이 막대하기 때문에 어플리케이션 전체에서 딱 한번 생성하고 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook"); // persistece.xml 설정 정보를 사용해서 엔티티 매니저 팩토리를 생성함.
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저를 생성, 엔티티 매니저를 사용해서 엔티티를 데이터베이스에 등록/수정/삭제 할 수 있음.
        EntityTransaction tx = em.getTransaction(); // 트랜잭션을 받아옮

        System.out.println("시작");
        try{
            tx.begin();
            logic(em); // 로직 실행
            tx.commit(); // 트랜잭션 커밋
        }catch (Exception e){
            tx.rollback(); // 예외 발생시 롤백
        }finally {
            em.close();
        }
        emf.close();
    }
    public static void logic(EntityManager em) {

        String id = "id1";

        Member member = new Member();
        member.setId(id);
        member.setUsername("영준");
        member.setAge(28);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        //JPQL, SQL을 JPA가 추상화한  JPQL 객체지향 언어 쿼리
        // Member는 테이블이 아닌 엔ㅌ티 객체를 뜻함
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);

    }
}
