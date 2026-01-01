package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;

public interface MemberRepository {
    public void save(String name);
    public Member findById(Long id);
    public List<Member> findByName(String name);
    public List<Member> findAll();
}
