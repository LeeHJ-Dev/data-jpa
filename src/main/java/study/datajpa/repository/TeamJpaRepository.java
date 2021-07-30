package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {

    @PersistenceContext
    private EntityManager em;

    //팀저장
    public Team save(Team team){
        em.persist(team);
        return team;
    }

    //팀삭제
    public void delete(Team team){
        em.remove(team);
    }


    //팀전체조회
    public List<Team> findAll(){
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }

    //팀단건조회
    public Optional<Team> findById(Long id){
        Team team1 = em.find(Team.class, id);
        return Optional.ofNullable(team1);
    }

    //팀카운트
    public Long count(){
        return em.createQuery("select count(t) from Team t", Long.class).getSingleResult();
    }


}
