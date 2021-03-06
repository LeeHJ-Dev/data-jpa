package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString(of = {"id","name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    List<Member> members = new ArrayList<>();


    public Team(String name) {
        this.name = name;
    }
}
