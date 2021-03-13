package main.service.member;

import main.DAO.member.MemberDAO;
import main.DTO.MemberDTO;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class MemberServiceImpl implements MemberService {
    private final BCryptPasswordEncoder passwordEncoder;

    private final MemberDAO memberDAO;

    public MemberServiceImpl(BCryptPasswordEncoder passwordEncoder, MemberDAO memberDAO) {
        this.passwordEncoder = passwordEncoder;
        this.memberDAO = memberDAO;
    }

    @Override
    public void registerMember(MemberDTO memberDTO) {
        memberDAO.registerMember(memberDTO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return memberDAO.login(memberDTO);
    }

    @Override
    public int duplicatedIdChk(MemberDTO memberDTO) {
        return memberDAO.duplicatedIdChk(memberDTO);
    }

    @Override
    public int duplicatedEmailChk(MemberDTO memberDTO) {
        return memberDAO.duplicatedEmailChk(memberDTO);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        memberDAO.updateMember(memberDTO);
    }

    @Override
    public void sendEmail(MemberDTO memberDTO, String div) {
        String charSet = "utf-8";
        String hostSMTP = "smtp.gmail.com"; //네이버 이용시 smtp.naver.com
        String hostSMTPid = "yeoncheol.jang@gmail.com";
        String hostSMTPpwd = "duscjf135789**";

        // 보내는 사람 EMail, 제목, 내용
        String fromEmail = "yeoncheol.jang@gmail.com";
        String fromName = "와인검색 사이트";
        String subject = "";
        String msg = "";

        if(div.equals("findPwd")) {
            subject = "와인검색 사이트 임시 비밀번호 안내";
            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
            msg += "<h3 style='color: blue;'>";
            msg += memberDTO.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
            msg += "<p>임시 비밀번호 : ";
            msg += memberDTO.getPwd() + "</p></div>";
        }

        // 받는 사람 E-Mail 주소
        String mail = memberDTO.getEmail();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSL(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(465); //네이버 이용시 587

            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setTLS(true);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
        } catch (Exception e) {
            System.out.println("메일발송 실패 : " + e);
        }
    }

    /*
     * 찾으려는 비밀번호의 이메일, 아이디가 맞는지 확인
     * 임시 비밀번호 생성
     * sendEmail로 raw 비밀번호 전송
     */
    @Override
    public void findPw(HttpServletResponse resp, MemberDTO memberDTO) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        MemberDTO ck = memberDAO.readMember(memberDTO.getId());
        PrintWriter out = resp.getWriter();

        // 가입된 아이디가 없으면
        if (memberDAO.duplicatedIdChk(memberDTO) == 0) {
            System.out.println("==================== 등록 X 아이디 =================");
            out.print("등록되지 않은 아이디입니다.");
            out.close();
        }
        // 가입된 이메일이 아니면
        else if (!memberDTO.getEmail().equals(ck.getEmail())) {
            System.out.println("==================== 등록 X 이메일 =================");
            out.print("등록되지 않은 이메일입니다.");
            out.close();
    }
        else {
            // 임시 비밀번호 생성
            String rawPwd = "";
            for (int i = 0; i < 6; i++) {
                rawPwd += (char) ((Math.random() * 26) + 97); // ASCII 코드 규칙상 97 = a, 즉 알파벳 26개 랜덤 출력
                rawPwd += String.valueOf((int) (Math.random() * 26)); // 알파벳 뒤 정수 섞기
            }

            // raw 임시 비밀번호 이메일 발송
            memberDTO.setPwd(rawPwd);
            sendEmail(memberDTO, "findPwd");

            // raw 비밀번호 encode 후 DB 저장
            String encodedPwd = passwordEncoder.encode(rawPwd);
            memberDTO.setPwd(encodedPwd);
            memberDAO.updateMember(memberDTO);

            out.print("이메일로 임시 비밀번호를 발송하였습니다.");
            out.close();
        }
    }
}
