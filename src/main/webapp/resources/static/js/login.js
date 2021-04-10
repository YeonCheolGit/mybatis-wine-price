$(document).ready(function () {
    let loginEmail = document.getElementById("login-email");
    let loginPwd = document.getElementById("login-pwd");
    let loginValidChk = document.getElementById("login-email-validation-chk");
    let findPwModalButton = document.getElementById("find-pw-modal-button");
    let findPwModalForm = document.getElementById("find-pw-modal-form");

    // 비밀번호 찾기 모달 form 버튼
    $(findPwModalButton).click(function () {
        $(findPwModalForm).modal("show")
    });

    // 로그인 이메일 입력 시 유효한 이메일 검증
    let emailRegex = /^[a-zA-Z0-9]+(\.)?[a-zA-Z0-9]*@[a-zA-Z]+\.[a-zA-Z]+\.?[a-zA-Z]*$/;
    let emailChkBoolean;
    $(loginEmail).on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사
        if (emailRegex.test($(loginEmail).val()) === false) {
            $(loginValidChk).text('유요한 이메일이 아닙니다.');
            emailChkBoolean = false;
        } else {
            $(loginValidChk).text('');
            emailChkBoolean = true;
        }
    });

    /**
     * 로그인 모달 form - 로그인 버튼
     * 1. 누락된 데이터 체크
     * 2. 로그인 성공 (data) 시 /main redirect
     * 3. 로그인 실패 (data === null) 시 alert
     */
    let loginSubmit = document.getElementById("login-submit");
    $(loginSubmit).click(function () {
        $.ajax({
            url: "/member/login",
            type: "post",
            dataType: "json",
            data: {
                "email": $(loginEmail).val(),
                "pwd": $(loginPwd).val(),
            },
            success: function (data) {
                if (data === null) { // 실패
                    alert("이메일 또는 비밀번호를 확인해주세요.");
                } if (data === 1) { // 정지된 회원
                    alert("정지된 회원 입니다.");
                } else if (data) { // 성공
                    $(loginSubmit).attr("value", "Y");
                    alert("환영합니다.");
                    self.location = "/main";
                }
            }
        });
    }); // 로그인 버튼 끝
});