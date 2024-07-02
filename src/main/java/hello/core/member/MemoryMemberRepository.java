package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository
{
    //동시성 이슈 때문에 cuncurrentHashMap을 사용해야하지만 여기서는 간단하게 HashMap을 사용
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
