$(document).ready(function () {
    let updateEmail = document.getElementById("update-email");
    let updatePwd = document.getElementById("update-pwd");
    let updateNickName = document.getElementById("update-nickname");
    let updatePwdValidChk = document.getElementById("update-pwd-validation-chk");

    /*
     * 비밀번호 규칙
     * - 최소 8글자
     * - 최소 1개의 소문자 && 대문자
     * - 최소 1개의 숫자
     * - 최소 1개의 특수문자
     */
    let pwdRegex = /^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\w!@#$%^&*]{8,}$/;
    let updatePwdChkBoolean = false;
    $('#update_pwd').on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사
        if (pwdRegex.test($('#update_pwd').val())) {
            $(updatePwdValidChk).text('비밀번호로 적합합니다.');
            updatePwdChkBoolean = true;
        } else {
            $(updatePwdValidChk).text('1개의 대문자 / 특수기호가 포함되어야 합니다.');
            updatePwdChkBoolean = false;
        }
    });

    /**
     * 회원수정 모달 form - 수정 버튼
     * 1. 누락된 비밀번호 체크
     * 2. 비밀번호 변경 후 /main redirect
     */

    $('#update_submit').click(function () {
        if (updatePwdChkBoolean === true) {
            $.ajax({
                url: "/member/updateMember",
                type: "post",
                dataType: "json",
                data: {
                    "email": $(updateEmail).val(),
                    "pwd": $(updatePwd).val(),
                    "nickName": $(updateNickName).val(),
                },
                success: function (data) { // 회원정보 수정 후 return
                    if (data === false) {
                        alert("비밀번호를 확인해주세요.");
                    } else if (data === true) {
                        alert("회원정보 수정이 완료 됐습니다.");
                        self.location = "/main";
                    }
                }
            });
        } else {
            alert("유효한 비밀번호 양식이 아닙니다.");
        }
    });
});