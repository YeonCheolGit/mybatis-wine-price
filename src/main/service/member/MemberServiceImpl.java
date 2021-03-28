package main.service.member;

import lombok.extern.log4j.Log4j2;
import main.DAO.member.MemberDAO;
import main.DTO.MemberDTO;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService{
    private BCryptPasswordEncoder passwordEncoder;

    private MemberDAO memberDAO;

    public MemberServiceImpl() {
    }

    @Autowired
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
    public int duplicatedNickNameChk(MemberDTO memberDTO) {
        return memberDAO.duplicatedNickNameChk(memberDTO);
    }

    @Override
    public int duplicatedEmailChk(MemberDTO memberDTO) {
        return memberDAO.duplicatedEmailChk(memberDTO);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        memberDAO.updateMember(memberDTO);
    }

    /*
     * 임시 비밀번호 전송
     */
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
            msg += memberDTO.getNickName() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
            msg += "<p>임시 비밀번호 : ";
            msg += memberDTO.getPwd() + "</p></div>";
        }

        // 받는 사람 E-Mail 주소 (회원가입 시 email로)
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
     1. 찾으려는 비밀번호의 이메일, 아이디 검증
     2. 임시 비밀번호 생성
     3. sendEmail() <-- raw 비밀번호 전송
     */
    @Override
    public String findPwd(MemberDTO memberDTO) {

        // 아이디 && 닉네임 없으면
        if (memberDAO.duplicatedEmailChk(memberDTO) == 0 && memberDAO.duplicatedNickNameChk(memberDTO) == 0) {
            log.debug("==================== 등록 X 이메일 & 닉네임 =================");
            return "3";
        }
        // 가입된 이메일이 없으면
        if (memberDAO.duplicatedEmailChk(memberDTO) == 0) {
            log.debug("==================== 등록 X 이메일 =================");

            return "1";
        }
        // 가입된 아이디가 없으면
        else if (memberDAO.duplicatedNickNameChk(memberDTO) == 0) {
            log.debug("==================== 등록 X 닉네임 =================");

            return "2";
        }
        // 이메일, 아이디 다 있으면
        else {
            // 임시 비밀번호 생성
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                stringBuilder.append((char) ((Math.random() * 26) + 97)); // ASCII 코드 규칙상 97 = a, 즉 알파벳 26개 랜덤 출력
                stringBuilder.append((int) (Math.random() * 26)); // 알파벳 뒤 정수 섞기
            }

            // raw 임시 비밀번호 이메일 발송
            memberDTO.setPwd(stringBuilder.toString());
            sendEmail(memberDTO, "findPwd");

            // raw 비밀번호 encode 후 DB 저장
            String encodedPwd = passwordEncoder.encode(stringBuilder);
            memberDTO.setPwd(encodedPwd);
            memberDAO.updateMember(memberDTO);

            return "true";
        }
    }
}
