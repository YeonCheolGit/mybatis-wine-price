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

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
     * 회원가입 버튼 클릭 시 동작
     */
    @PostMapping(value = "/registerMember")
    @ResponseBody
    public Boolean registerMember(MemberDTO memberDTO) {
        log.debug("==================== registerMember ====================");

        return memberService.registerMember(memberDTO);
    }

    /*
     * 로그인 클릭 시 동작
     * if 이메일 일치 X || 비밀번호 일치 X || 정지 회원 경우
     *  return null;
     * else
     *  return MemberDTO 객체;
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(MemberDTO memberDTO, HttpServletRequest request) {
        log.debug("==================== login ====================");

        return memberService.login(memberDTO, request);
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
    public int duplicated_email_chk(MemberDTO memberDTO) {
        log.debug("==================== duplicatedEmailChk ====================");
        return memberService.duplicatedEmailChk(memberDTO);
    }

    /*
     * 회원정보 받아서 비밀번호 업데이트
     * 1. 사용자 입력 새 pwd 인코딩
     * 2. DB에 새 pwd 저장
     * 3. 로그아웃 - 세션 만료
     * 4. return main 페이지
     */
    @PostMapping(value = "/updateMember")
    @ResponseBody
    public Boolean updateMember(MemberDTO memberDTO,
                               HttpServletRequest request) {
        log.debug("==================== updateMember ====================");

        return  memberService.updateMember(memberDTO, request); // 인코딩 된 비밀번호로 회원정보 업데이트
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
        return memberService.findPwd(memberDTO);
    }
}