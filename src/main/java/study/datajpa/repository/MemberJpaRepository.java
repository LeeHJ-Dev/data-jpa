package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    //
    @PersistenceContext
    private EntityManager em;

    //회원저장
    public Member save(Member member){
        em.persist(member);
        return member;
    }

    //회원삭제
    public void delete(Member member){
        em.remove(member);
    }

    //회원전체조회
    public List<Member> findAll(){
        //jpql
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        return members;
    }

    //회원단건조회(옵셔널)
    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //
    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    //회원조회(단건)
    public Member find(Long id){
        return em.find(Member.class, id);
    }

    //
    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age){
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age", Member.class)
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Member> findByPage(int age, int offset, int limit){
        List<Member> memberList = em.createQuery("select m from Member m where m.age = :age order by m.username desc ", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return memberList;

    }

    public Long totalCount(int age){
        Long totcalCnt = em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();

        return totcalCnt;

    }

    public int bulkAgePlus(int age){
        int updCnt = em.createQuery("update Member m set m.age = m.age+1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
        return updCnt;
    }



}
