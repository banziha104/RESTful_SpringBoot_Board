package RELATION_01.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne // 다대일 관계라는 매핑정보, 필수
    @JoinColumn(name = "TEAM_ID") // 외래키 매핑시 사용, name 속성에는 매핑할 외래 키 이름 지정
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
