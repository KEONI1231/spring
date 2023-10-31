package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//실제 인터페이스를 implements하여 함수 내용 구현.
public class MemoryMemberRepository implements MemberRepository {

    //MemoryMemberRepository 는 데이터에 접근하고 관리하는 로직만을 담당.
    //즉 주로 데이터 저장소와의 상호작용 을 담당.여기서는 메모리에 저장하는 로직을 가지고 있음.
    //데이터를 어떻게 젖아하고, 조회하고, 수정하고, 삭제할 것인가? 에 관한 로직이 위치.
    //그러나 "어떤 데이터를 저장해야 하는가? 또는 저장해선 안되는가?"와 같은 비즈니스 규칙은
    //포함되어 있지 않다.

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //values으로 map의 모든 값들을 collection으로 반환,  구후 collection을 stream으로 반환
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
