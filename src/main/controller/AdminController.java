package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.MemberDTO;
import main.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/adminPage.do")
    public ModelAndView adminPage(Model model) {
        System.out.println("===================== adminPage =================");
        model.addAttribute("allMemberList", memberService.allMemberList());
        return new ModelAndView("admin");
    }

    /*
     * Admin.jsp 계정 정지 버튼 클릭 시
     * 해당 계정 enabled <- 0
     * return true;
     */
    @PostMapping(value = "/enabledPause")
    @ResponseBody
    public int enabledPause(MemberDTO memberDTO) {
        System.out.println("===================== enabledPause =================");

        memberDTO.setEnabled(0);

        return memberService.enabledControl(memberDTO);
    }
    /*
     * Admin.jsp 계정 활성화 버튼 클릭 시
     * 해당 계정 enabled <- 1
     * return true;
     */
    @PostMapping(value = "/enabledActive")
    @ResponseBody
    public int enabledActive(MemberDTO memberDTO) {
        System.out.println("===================== enabledActive =================");

        memberDTO.setEnabled(1);

        return memberService.enabledControl(memberDTO);
    }
}
