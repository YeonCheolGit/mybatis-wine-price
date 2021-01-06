package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.MemberDTO;
import main.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping(value = "/member")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private static HttpSession session;
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    @GetMapping
    Move to member register form
    registerMember.jsp
     */
    @GetMapping(value = "/goRegisterMemberForm")
    public String goRegisterMemberForm() {
        logger.debug("goRegisterMemberForm");
        return "member/registerMember";
    }

    /*
    @PostMapping
    Do register member
     */
    @PostMapping(value = "/registerMember")
    public String registerMember(MemberDTO memberDTO) {
        memberService.registerMember(memberDTO);
        return "redirect:/";
    }

    /*
    Go Login Form
     */
    @GetMapping(value = "/loginForm")
    public String goLoginForm() {
        logger.debug("goLoginForm");
        return "member/loginFormTest";
    }

    /*
    Do login
    If login data doesn't come from view <-- null
     */
    @PostMapping(value = "/login")
    public String login(MemberDTO memberDTO, HttpServletRequest req, RedirectAttributes rttr) {
        logger.debug("login");

        session = req.getSession();
        MemberDTO login = memberService.login(memberDTO);

        /*
        로그인 시도를 안했으니, 찾은 것도 null then return 'main'
        로그인 후 session에 member 이름으로 로그인 정보 then return 'main'
         */
        if(login == null) {
            session.setAttribute("member", null);
            rttr.addFlashAttribute("msg", false);
        } else {
            session.setAttribute("member", login);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

}
