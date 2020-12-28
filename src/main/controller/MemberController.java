package main.controller;

import main.DTO.MemberDTO;
import main.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/registerMember")
    public String goRegisterMemberForm() {
        logger.info("goRegisterMemberForm");
        return "member/registerMember";
    }


    @PostMapping(value = "/registerMember")
    public String registerMember(MemberDTO memberDTO) {
        logger.info("registerMember");
        memberService.registerMember(memberDTO);
        return "redirect:/wine/allWineList";
    }
}
