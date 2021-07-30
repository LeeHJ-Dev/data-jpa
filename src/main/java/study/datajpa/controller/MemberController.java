package study.datajpa.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping(value = "/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        return memberRepository.findById(id).get().getUsername();
    }

    @GetMapping(value = "/members")
    public Page<Member> findAll(@PageableDefault(size = 5) Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        return members;
    }

    @GetMapping(value = "/members_dto")
    public Page<MemberDto> findDtoAll(Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        Page<MemberDto> memberDtos = members.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        return memberDtos;
    }

    @GetMapping(value = "/members_dto2")
    public Page<MemberDto> findDtoAll2(Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        Page<MemberDto> memberDtos = members.map(member -> new MemberDto(member));
        return memberDtos;
    }

    @PostConstruct
    public void init(){
        for(int i=0; i<100; i++){
            memberRepository.save(new Member("member" + i, i, null));
        }
    }

}
