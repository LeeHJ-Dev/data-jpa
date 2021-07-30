package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    //회원이름조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //study.datajpa.dto
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();


    //page
    Page<Member> findByAge(int age, Pageable pageable);

    //일괄수정
    @Modifying(clearAutomatically = true)
    @Query(value =
            "update Member m " +
            "   set m.age = m.age + 1 " +
            " where m.age >= :age ")
    int bulkAgePlus(@Param("age") int age);

}
