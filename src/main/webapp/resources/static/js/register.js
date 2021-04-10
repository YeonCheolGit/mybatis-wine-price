$(document).ready(function () {
    let registerEmail = document.getElementById("register-email")
    let registerPwd = document.getElementById("register-pwd");
    let registerNickName = document.getElementById("register-nickname");
    let registerSubmit = document.getElementById("register-submit");
    let registerEmailValidChk = document.getElementById("register-email-validation-chk");
    let pwdValidChk = document.getElementById("pwd-validation-chk")
    /*
     * 이메일 규칙
     * -영문으로 이루어짐
     * -중간에 .이 와도 되고 안와도 됨.
     * - @ 반드시 와야 함
     * - co.kr / com 상관 X
     */

    let emailRegex = /^[a-zA-Z0-9]+(\.)?[a-zA-Z0-9]*@[a-zA-Z]+\.[a-zA-Z]+\.?[a-zA-Z]*$/;
    let emailChk_boolean = false;
    $(registerEmail).on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사
        if (emailRegex.test($(registerEmail).val())) {
            $(registerEmailValidChk).text('이메일로 적합합니다.');
            emailChk_boolean = true;
        } else {
            $(registerEmailValidChk).text('유효한 이메일이 아닙니다.');
            emailChk_boolean = false;
        }
    });

    /*
     * 비밀번호 규칙
     * - 최소 8글자
     * - 최소 1개의 소문자 && 대문자
     * - 최소 1개의 숫자
     * - 최소 1개의 특수문자
     */

    let pwdRegex = /^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\w!@#$%^&*]{8,}$/;
    let pwdChk_boolean = false;
    $(registerPwd).on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사
        if (pwdRegex.test($(registerPwd).val())) {
            $(pwdValidChk).text('비밀번호로 적합합니다.');
            pwdChk_boolean = true;
        } else {
            $(pwdValidChk).text('1개의 대문자 / 특수기호가 포함되어야 합니다.');
            pwdChk_boolean = false;
        }
    });

    // 회원가입 모달 form - 이메일 중복 체크 버튼
    $('#emailChk').click(function () {
        if (emailChk_boolean === true) {
            $.ajax({
                url: "/member/duplicatedEmailChk",
                type: "post",
                dataType: "json",
                data: {
                    "email": $(registerEmail).val(),
                },
                success: function (data) { // 버튼 클릭 후 return
                    if (data === 1) {
                        alert("이미 사용하고 있는 이메일입니다.");
                    } else if (data === 0) {
                        $("#emailChk").attr("value", "Y");
                        alert("사용 가능한 이메일입니다.");
                    }
                }
            });
        } else {
            alert("유효한 이메일 양식이 아닙니다.");
        }
    }); // 이메일 중복 체크 버튼 끝

    /*
     * 회원가입 모달 form - 가입 버튼
     * 1. 이메일, 비밀번호, 아이디 누락 정보 체크
     * 2. 유효한 이메일, 비밀번호 체크
     * 3. 중복 이메일 체크
     * 4. 회원 가입 완료
     */

    $(registerSubmit).click(function () {
        // 회원가입 시 미입력 정보 팝업
        if ($(registerEmail).val() === "") {
            alert("이메일을 입력해주세요.");
            $(registerEmail).focus();
            return false;
        }
        else if ($(registerPwd).val() === "") {
            alert("비밀번호를 입력해주세요.");
            $(registerPwd).focus();
            return false;

        }
        else if ($(registerNickName).val() === "") {
            alert("아이디를 입력해주세요.");
            $(registerNickName).focus();
            return false;
        }
        else if (pwdChk_boolean === true && emailChk_boolean === true) { // 유효한 이메일 && 비밀번호 입력 시
            $.ajax({
                url: "/member/registerMember",
                type: "post",
                dataType: "json",
                data: {
                    "email": $(registerEmail).val(),
                    "pwd": $(registerPwd).val(),
                    "nickName": $(registerNickName).val(),
                },
                success: function (data) { // 회원 가입 버튼 클릭 후 return
                    if (data === false) {
                        alert("중복되는 이메일 혹은 아이디 입니다!");
                    } else if (data === true) {
                        alert("환영합니다!");
                        self.location = "/main";
                    }
                }
            });
        } else { // 회원가입 버튼 클릭 후 적합한 이메일 || 비밀번호 아닐 시
            alert("유효한 이메일 혹은 비밀번호가 아닙니다.")
        }
    }); // 가입 버튼 태그 끝
});