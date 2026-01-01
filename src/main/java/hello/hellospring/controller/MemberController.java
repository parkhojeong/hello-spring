package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberCreateRequest request){
        Member member = new Member();
        member.setName(request.getName());
        memberService.join(member);
        return ResponseEntity.status(201).body(new MemberResponse(request.getName()));

    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> members(){
        List<MemberResponse> memberResponses = memberService.findMembers().stream().map(member -> new MemberResponse(member.getName())).toList();
        return ResponseEntity.ok(memberResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable("id") Long id){
        Member byId = memberService.findOne(id).orElseThrow();
        if(byId == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MemberResponse(byId.getName()));
    }
}
