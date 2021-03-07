package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.MemberDTO;
import main.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping(value = "/member")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private static HttpSession session;
    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    /*
     * 회원가입 버튼 클릭 시 동작
     */
    @PostMapping(value = "/registerMember")
    @ResponseBody
    public String registerMember(MemberDTO memberDTO,
                                               HttpServletRequest req) {
        logger.debug("registerMember debug >>> ");

        int result = memberService.duplicatedIdChk(memberDTO);
        session = req.getSession();

        if (result == 0) {
            String rawPwd = memberDTO.getPwd(); // 사용자가 입력한 raw 비밀번호
            String encodedPwd = passwordEncoder.encode(rawPwd); // raw 비밀번호를 인코딩
            memberDTO.setPwd(encodedPwd);

            memberService.registerMember(memberDTO);
            return "true";
        } else {
            session.setAttribute("member", null);
            return "null";
        }
    }

    /*
     * 로그인 클릭 시 동작
     * return <-- 로그인 결과 (true | null)
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(MemberDTO memberDTO,
                                      HttpServletRequest req) {
        logger.debug("login debug >>> ");

        session = req.getSession();

        MemberDTO login = memberService.login(memberDTO);
        boolean pwdMatch = passwordEncoder.matches(memberDTO.getPwd(), login.getPwd());

        if (login == null || !pwdMatch) {
            session.setAttribute("member", null);
            return "null";
        } else {
            session.setAttribute("member", login);
            return "true";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        logger.info("logout debug >>> ");
        session.invalidate();
        return "redirect:/";
    }

    /*
     * 회원가입 중 중복체크 클릭 시 동작
     * return <-- true | false
     */
    @RequestMapping(value = "/duplicatedIdChk", method = RequestMethod.POST)
    @ResponseBody
    public int duplicatedIdChk(MemberDTO memberDTO) {
        logger.debug("duplicatedIdChk debug >>> ");
        return memberService.duplicatedIdChk(memberDTO);
    }

    /*
     * 회원정보 받아서 비밀번호 업데이트
     * DB와 업데이트 후 로그아웃 세션 진행
     */
    @PostMapping(value = "/updateMember")
    @ResponseBody
    public String updateMember(MemberDTO memberDTO,
                               HttpServletRequest req) {
        logger.debug("updateMember debug >>> ");

        String rawPwd = memberDTO.getPwd();
        String encodedPwd = passwordEncoder.encode(rawPwd); // 비밀번호 인코딩
        memberDTO.setPwd(encodedPwd);

        try {
            memberService.updateMember(memberDTO); // 회원정보 업데이트
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
