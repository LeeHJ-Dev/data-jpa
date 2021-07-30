package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString(of = {"id","username","age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String userName) {
        this(userName,0);
    }

    public Member(String username, int age) {
        this(username,age,null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null){
            this.changeTeam(team);
        }
    }

    //연관관계 메서드(팀이 변경되었다면)
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

}
