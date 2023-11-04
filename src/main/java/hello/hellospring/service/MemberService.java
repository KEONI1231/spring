package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//구현된 implements를 사용.
//얘는 순수한 자바 클래스임. 그래서 어노테이션으로 service 등록을 해줘야댐

//@Service
public class MemberService { //memberservice는 비즈니스 로직을 담당.
    //비즈니스 로직을 담당. 예를 들어 "같은 회원 이름을 사용할 수 없다" 라는 비즈니스 규칙을 구현하기 위해,
    //회원가입을 요청받았을 때 먼저 MemberRepository를 사용하여 동일한 이름을 가진 회원이 있는지 검사.
    //그후 저장하는 등의 로직
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *회원가입
     */
    public Long join (Member member) {
        //같은 이름이 있는 중복 회원 x
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = "+ timeMs + "ms");
        }
    }
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입이다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //naming을 좀더 비즈니스에 가까운 이름으로.
    //레퍼지토리는 개발스러운
    //서비스는 비즈니스를 처리하는 느낌의 네이밍
}