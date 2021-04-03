<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>회원가입 모달</title>
</head>
<body>
<%-- 회원가입 모달 --%>
<div class="modal fade" id="register_modal_form" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content-register">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">와인 검색 사이트</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="register_email" class="form-label">이메일</label>
                    <input type="email" id="register_email" name="email" class="form-control" placeholder="example@email.com">
                    <div id="register_email_validation_chk"></div>
                    <button type="button" class="btn btn-outline-warning" id="emailChk" value="N">중복확인</button>
                </div>
                <div class="mb-3">
                    <label for="register_pwd" class="form-label">비밀번호</label>
                    <input type="password" id="register_pwd" name="pwd" class="form-control">
                    <div id="pwd_validation_chk"></div>
                </div>
                <div class="mb-3">
                    <label for="register_nickName" class="form-label">아이디</label>
                    <input type="text" id="register_nickName" name="nickName" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="submit" class="btn btn-primary" id="register_submit">회원가입</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        /*
         * 이메일 규칙
         * -영문으로 이루어짐
         * -중간에 .이 와도 되고 안와도 됨.
         * - @ 반드시 와야 함
         * - co.kr / com 상관 X
         */
        let emailRegex = /^[a-zA-Z0-9]+(\.)?[a-zA-Z0-9]*@[a-zA-Z]+\.[a-zA-Z]+\.?[a-zA-Z]*$/;
        let emailChk_boolean = false;
        $('#register_email').on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사
            if (emailRegex.test($('#register_email').val())) {
                $('#register_email_validation_chk').text('이메일로 적합합니다.');
                emailChk_boolean = true;
            } else {
                $('#register_email_validation_chk').text('유효한 이메일이 아닙니다.');
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
        $('#register_pwd').on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사
            if (pwdRegex.test($('#register_pwd').val())) {
                $('#pwd_validation_chk').text('비밀번호로 적합합니다.');
                pwdChk_boolean = true;
            } else {
                $('#pwd_validation_chk').text('1개의 대문자 / 특수기호가 포함되어야 합니다.');
                pwdChk_boolean = false;
            }
        });

        // 회원가입 모달 form - 이메일 중복 체크 버튼
        $('#emailChk').click(function () {
            if (emailChk_boolean === true) {
                $.ajax({
                    url: "${contextPath}/member/duplicated_email_chk",
                    type: "post",
                    dataType: "json",
                    data: {
                        "email": $("#register_email").val(),
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
         * 회원가입 모달 from - 가입 버튼
         * 1. 이메일, 비밀번호, 아이디 누락 정보 체크
         * 2. 유효한 이메일, 비밀번호 체크
         * 3. 중복 이메일 체크
         * 4. 회원 가입 완료
         */
        $('#register_submit').click(function () {
            // 회원가입 시 미입력 정보 팝업
            if ($("#register_email").val() === "") {
                alert("이메일을 입력해주세요.");
                $("#register_email").focus();
                return false;
            }
            else if ($("#register_pwd").val() === "") {
                alert("비밀번호를 입력해주세요.");
                $("#register_pwd").focus();
                return false;

            }
            else if ($("#register_nickName").val() === "") {
                alert("아이디를 입력해주세요.");
                $("#register_nickName").focus();
                return false;
            }
            else if (pwdChk_boolean === true && emailChk_boolean === true) { // 유효한 이메일 && 비밀번호 입력 시
                $.ajax({
                    url: "${contextPath}/member/registerMember",
                    type: "post",
                    dataType: "json",
                    data: {
                        "email": $("#register_email").val(),
                        "pwd": $("#register_pwd").val(),
                        "nickName": $("#register_nickName").val(),
                    },
                    success: function (data) { // 회원 가입 버튼 클릭 후 return
                        if (data === false) {
                            alert("중복되는 이메일 혹은 아이디 입니다!");
                        } else if (data === true) {
                            alert("환영합니다!");
                            self.location = "${contextPath}/main";
                        }
                    }
                });
            } else { // 회원가입 버튼 클릭 후 적합한 이메일 || 비밀번호 아닐 시
                alert("유효한 이메일 혹은 비밀번호가 아닙니다.")
            }
        }); // 가입 버튼 태그 끝
    });
</script>
</body>
</html>
