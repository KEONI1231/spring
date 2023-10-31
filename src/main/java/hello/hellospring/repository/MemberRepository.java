package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//기본 유저객체를 사용. 예상되는 함수 정의.
public interface MemberRepository { //interface -> 부모 객체는 선언만 하며 정의(내용)은 자식에서 오버라이딩하여 사용해야함
    //반면 extends는 부모에서 선언/ 정의를 모두하며 자식은 메소드 / 변수를 그대로 사용할 수 있음.
    //abstract -> extends 와 interface의 혼합. extends하되 몇 개는 추상 메소드로 구현되어 있음.
    Member save(Member member);
    Optional<Member> findById(Long id); //옵셔녈?
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
