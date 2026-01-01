package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        System.out.println("MemberController.createForm");
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
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
