# JPA 

> 자바의 ORM 기술 표준 , 애플리케이션고 JDBC 사이에서 동작함, 하이버네이트를 기반으로 제작 

<br >

### 객체 매핑 

- @Entity : 해당 클래스를 테이블과 매핑한다고 JPA에게 알려줌
- @Table : 엔티티 클래스에 매핑할 테이블 정보를 제공
- @Id :  xpdlqmfdml rlqhs zlfmf aovldgka
- @Column : 필드를 칼럼에 매핑
- 없는 경우 : 매핑 어노테이션 생략시, 필드명을 사용해서 컬럼명으로 자동매핑

```java
package JPA_BASIC_00;

import javax.persistence.*;  //**

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

```

<br>

## 메인

```java
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

```


<br>

## 영속성 컨텐츠와 생명주기

> 영속은 엔티티를 영구 저장하는 환경을 뜻함.

- 엔티티의 생명주기
    - 비영속 : 영속성 컨텍스트와 관계없는 상태 
    - 영속 : 영속성 컨텍스트에 저장된 상태
    - 준영속 : 영속성 컨텍스트에 저장되었다가 분리된 상태
    - 삭제 : 삭제된 상태 

- flush() : 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영한다. 커밋시 자동 실행
- detach() : 특정 엔티티를 준영속 상태로 만듬. 영속성 컨텍스트로부터 분리된 상태
- clear() : 영속성 초기화 
- close() : 영속성 컨텍스트 종료
- merge() : 준영속 상태의 엔티티를 다시 영속상태로 변경

