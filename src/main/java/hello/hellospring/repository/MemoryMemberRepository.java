package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private List<Member> members = new ArrayList<>();

    @Override
    public void save(String name) {
        members.add(new Member((long) members.size(), name));
    }

    @Override
    public Member findById(Long id) {
        for (Member member : members) {
            if(member.getId().equals(id)){
                return member;
            }
        }

        return null;
    }

    @Override
    public List<Member> findByName(String name) {
        List<Member> result = new ArrayList<>();
        for (Member member : members) {
            if(member.getName().equals(name)){
                result.add(member);
            }
        }

        return result;
    }

    @Override
    public List<Member> findAll() {
        return members;
    }


}
