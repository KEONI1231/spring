package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//구현된 implements를 사용.
public class MemberService { //memberservice는 비즈니스 로직을 담당.
    //비즈니스 로직을 담당. 예를 들어 "같은 회원 이름을 사용할 수 없다" 라는 비즈니스 규칙을 구현하기 위해,
    //회원가입을 요청받았을 때 먼저 MemberRepository를 사용하여 동일한 이름을 가진 회원이 있는지 검사.
    //그후 저장하는 등의 로직
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     *회원가입
     */
    public Long join (Member member) {
        //같은 이름이 있는 중복 회원 x

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
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
