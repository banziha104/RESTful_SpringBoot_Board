# 엔티티 매핑

- 데이터베이스 스키마 자동 생성 
    - create : 기존 테이블을 삭제하고 새로 생성
    - create-drop : create 속성에 추가로 애플리케이션을 종료할 때 생성한 DDL을 제거
    - update : 데이터베이스 테이블과 엔티티 매핑정보를 비교해서 변경 사항만 수정함
    - validate : 데이터베이스 테이블과 엔티티 매핑정보를 비교해서 차이가 있으면 경고를 남기고 어플리케이션을 실행하지 않음
    - none : 자동 생성 기능을 사용하지 않음 
    
```xml
<property name="hibernate.show_sql" value="true" />
<property name="hibernate.hbm2ddl.auto" value="update" />
```

<br>  

## DDL 생성 기능 

> 자동생성시에만 유효하며 JPA 실행 로직에 영향을 주지 않음

```java

@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint( //자동생성시 유니크 //**
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"} )}) 
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10) //추가 //**
//    @Column(name = "NAME") //추가 //**
    private String username;
    
    /*.......*/
    }

```

<br>

## 기본 키 매핑 

- 직접 할당 : 기본 키를 애플리케이션에서 직접 할당함

```java
// 직접할당의 경우
@Id
@Column(name = "ID")
private String id;

```

- 자동 생성
    - IDENTITY : 기본 키 생성을 데이터베이스에 위임함
    
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    ```

    - SEQUENCE : 데이터베이스 시퀸스를 사용해서 기본 키를 할당함
    - TABLE : 키 생성 테이블을 사용함
    - AUTO : 데이터베이스에 따라 IDENTITY, SEQUENCE, TABLE 전략 중 하나를 자동 선택
    