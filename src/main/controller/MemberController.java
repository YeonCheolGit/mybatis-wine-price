package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.MemberDTO;
import main.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    Do register member
     */
    @PostMapping(value = "/registerMember")
    public String registerMember(MemberDTO memberDTO) {
        logger.debug("registerMember debug >>> ");
        int result = memberService.duplicatedIdChk(memberDTO);

        if (result == 1) {
            return "redirect:/";
        } else if (result == 0) {
            memberService.registerMember(memberDTO);
        }
        return "redirect:/";
    }

    /*
    Do login
    return login form
     */
    @PostMapping(value = "/login")
    public @ResponseBody String login(MemberDTO memberDTO,
                                      HttpServletRequest req) {
        logger.debug("login debug >>> ");

        session = req.getSession();
        MemberDTO login = memberService.login(memberDTO);

        if (login == null) {
            session.setAttribute("member", null);
            return "null";
        } else {
            session.setAttribute("member", login);
            return "true";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        logger.debug("logout debug >>> ");
        session.invalidate();
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/duplicatedIdChk", method = RequestMethod.POST)
    public int duplicatedIdChk(MemberDTO memberDTO) {
        logger.debug("duplicatedIdChk debug >>> ");
        return memberService.duplicatedIdChk(memberDTO);
    }

    @PostMapping(value = "/updateMember")
    public String updateMember(MemberDTO memberDTO) {
        memberService.updateMember(memberDTO);
        session.invalidate();
        return "redirect:/";
    }
}
