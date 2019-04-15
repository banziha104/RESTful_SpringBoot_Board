# 연관관계

<br>

- 방향성 : 데이터가 참조 하는 방향
- 다중성 : 다대일 , 일대다 , 일대일, 다대다 
- 연관관계의 주인 : 양방향 연관관계를 만들려면 연관관계의 주인 필요

<br>

### 단방향 연관관계

```java
@Entity
public class Member {
    @Id 
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne // 다대일 관계라는 매핑정보, 필수
    @JoinColumn(name = "TEAM_ID") // 외래키 매핑시 사용, name 속성에는 매핑할 외래 키 이름 지정
    private Team team;
}

```

<br> 

