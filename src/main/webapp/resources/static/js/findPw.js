$(document).ready(function () {
    let findPwEmail = document.getElementById("find-pw-email");
    let findPwNickName = document.getElementById("find-pw-nickName");
    /**
     * 비밀번호 찾기 모달 form - 새 비밀번호 전송 버튼
     * 1. 가입된 이메일 체크
     * 2. 가입된 아이디 체크
     * 3. 임시 비밀번호 전송 후 /main redirect
     */
    let sendNewPwd = document.getElementById("send-new-pwd");
    $(sendNewPwd).click(function () {
        $.ajax({
            url: contextPath + "/member/findPwd",
            type: "post",
            dataType: "json",
            data: {
                "email": $(findPwEmail).val(),
                "nickName": $(findPwNickName).val(),
            },
            success: function (result) {
                if (result === 1) { // email 일치 X 경우
                    alert("이메일 혹은 닉네임을 다시 확인해주세요.")
                } else if (result === 2) { // 에러 발생 경우
                    self.location = "/main/errorPage";
                } else { // nickName && email 일치
                    alert("임시 비밀번호를 발송 했습니다");
                    self.location = contextPath + "/main";
                }
            }
        });
    }); // 새 비밀번호 전송 버튼 끝
});