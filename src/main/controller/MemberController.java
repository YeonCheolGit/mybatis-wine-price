package main.controller;

import lombok.extern.log4j.Log4j2;
import main.DTO.MemberDTO;
import main.service.member.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Log4j2 // log field 생성 lombok 애노테이션
@RequestMapping(value = "/member")
public class MemberController {

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
    public String registerMember(MemberDTO memberDTO) {
        log.debug("==================== registerMember ====================");

        int resultEmail = memberService.duplicatedEmailChk(memberDTO); // 중복 email 체크
        int resultNickName = memberService.duplicatedNickNameChk(memberDTO); // 중복 nickName 체크

        if (resultNickName == 0 && resultEmail == 0) { // 중복된 email && nickName X 경우
            String rawPwd = memberDTO.getPwd(); // 사용자가 입력한 raw 비밀번호
            String encodedPwd = passwordEncoder.encode(rawPwd); // raw 비밀번호를 인코딩
            memberDTO.setPwd(encodedPwd);

            memberService.registerMember(memberDTO);
            return "true";
        } else {
            return "null";
        }
    }

    /*
     * 로그인 클릭 시 동작
     * return <-- 로그인 결과 (true | null)
     */
//    @PostMapping(value = "/login")
//    @ResponseBody
//    public String login(MemberDTO memberDTO, HttpServletRequest req) {
//        log.debug("==================== login debug ====================");
//
//        HttpSession session = req.getSession();
//
//        try {
//            MemberDTO login = memberService.login(memberDTO); // 없는 email 입력 시 에러 발생 시킴
//
//            if (login.getEmail() == null) {
//                throw new Exception();
//            }
//            boolean pwdMatch = passwordEncoder.matches(memberDTO.getPwd(), login.getPwd()); // 잘못 된 pw 입력 시 에러 발생 시킴
//            if (!pwdMatch) {
//                throw new Exception();
//            }
//            session.setAttribute("member", login); // email, pw 일치할 시 true 반환, 세션 등록
//
//            if (login.getRole().equals("ROLE_ADMIN")) {
//                session.setAttribute("admin_session", login.getRole());
//            }
//
//            return "true";
//
//        } catch (Exception e) {
//            log.debug("=============== 로그인 에러 ===============");
//            log.debug(e.getMessage());
//
//            session.setAttribute("member", null); // 세션 null
//            return "null";
//        }
//    }

    /*
     * 로그인 클릭 시 동작
     * return <-- 로그인 결과 (true | null)
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(MemberDTO memberDTO, HttpServletRequest request) {
        log.debug("==================== login ====================");

        HttpSession session = request.getSession();

        try {
            MemberDTO login = memberService.login(memberDTO); // 사용자가 입력한 정보 바탕 DB 조회
            boolean pwdMatch = passwordEncoder.matches(memberDTO.getPwd(), login.getPwd()); // 찾아온 DB pwd, 사용자 입력 pwd 비교

            if (login.getEmail() == null || !pwdMatch) { // 사용자 입력 정보 DB의 email || pwd 일치 X 경우
                session.setAttribute("member", null); // 세션 null
                return "null";
            } else {
                session.setAttribute("member", login); // 일치한 경우
                if (login.getRole().equals("ROLE_ADMIN")) { // 일치 && ROLE_ADMIN 경우
                    session.setAttribute("admin_session", login.getRole()); // admin session 등록
                }
                return "true";
            }
        } catch (Exception e) {
            log.debug("=============== 로그인 에러 ===============");
            log.debug(e.getMessage());

            session.setAttribute("member", null); // 세션 null
            return "redirect:/main/errorPage"; // 예기치 못 한 에러 발생시 에러 페이지
        }
    }

    /*
     * 로그아웃 버튼 클릭 시 동작
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/main";
    }

    /*
     * 회원가입 중 중복체크 클릭 시 동작
     * return <-- true | false
     */
    @PostMapping(value = "/duplicatedEmailChk")
    @ResponseBody
    public int duplicatedNickNameChk(MemberDTO memberDTO) {
        log.debug("==================== duplicatedEmailChk ====================");
        return memberService.duplicatedEmailChk(memberDTO);
    }

    /*
     1. 회원정보 받아서 비밀번호 업데이트
     2. DB에 저장
     3. 로그아웃 세션
     */
    @PostMapping(value = "/updateMember")
    @ResponseBody
    public String updateMember(MemberDTO memberDTO,
                               HttpServletRequest req) {
        log.debug("==================== updateMember ====================");

        String rawPwd = memberDTO.getPwd(); // 사용자 입력 비밀번호
        String encodedPwd = passwordEncoder.encode(rawPwd); // 비밀번호 인코딩
        memberDTO.setPwd(encodedPwd); // 인코딩 된 비밀번호 저장

        try {
            memberService.updateMember(memberDTO); // 인코딩 된 비밀번호로 회원정보 업데이트
            HttpSession session = req.getSession();
            session.invalidate();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    /*
     1. 비밀번호 찾기 버튼
     2. 회원 이메일, 아이디 검증
     3. 검증 후 임시 비밀번호로 변경
     4. 변경 된 임시 비밀번호 이메일 발송
     5. return <-- true or null
     */
    @PostMapping(value = "/findPwd")
    @ResponseBody
    public String findPw(MemberDTO memberDTO) throws IOException {
        log.debug("==================== findPwd ====================");
        System.out.println(memberService.findPwd(memberDTO));
        return memberService.findPwd(memberDTO);
    }
}