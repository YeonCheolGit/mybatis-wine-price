<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>로그인 모달</title>
</head>
<body>
<%-- 로그인 모달--%>
<div class="modal fade" id="login-modal-form" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content-login">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">로그인 창</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="login-email" class="form-label">이메일</label>
                    <input type="email" id="login-email" name="email" class="form-control" placeholder="example@email.com">
                    <div id="login-email-validation-chk"></div>
                </div>
                <div class="mb-3">
                    <label for="login-pwd" class="form-label">비밀번호</label>
                    <input type="password" id="login-pwd" name="pwd" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="login-submit" value="N">로그인</button>
                <button type="button" class="btn btn-primary" id="find-pw-modal-button">비밀번호 찾기</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/login.js"></script>
<%--<script type="text/javascript">--%>
<%--    $(document).ready(function () {--%>
<%--        let loginEmail = document.getElementById("login-email");--%>
<%--        let loginPwd = document.getElementById("login-pwd");--%>
<%--        let loginValidChk = document.getElementById("login-email-validation-chk");--%>
<%--        let findPwModalButton = document.getElementById("find-pw-modal-button");--%>
<%--        let findPwModalForm = document.getElementById("find-pw-modal-form");--%>

<%--        // 비밀번호 찾기 모달 form 버튼--%>
<%--        $(findPwModalButton).click(function () {--%>
<%--            $(findPwModalForm).modal("show")--%>
<%--        });--%>

<%--        // 로그인 이메일 입력 시 유효한 이메일 검증--%>
<%--        let emailRegex = /^[a-zA-Z0-9]+(\.)?[a-zA-Z0-9]*@[a-zA-Z]+\.[a-zA-Z]+\.?[a-zA-Z]*$/;--%>
<%--        let emailChkBoolean;--%>
<%--        $(loginEmail).on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사--%>
<%--            if (emailRegex.test($(loginEmail).val()) === false) {--%>
<%--                $(loginValidChk).text('유요한 이메일이 아닙니다.');--%>
<%--                emailChkBoolean = false;--%>
<%--            } else {--%>
<%--                $(loginValidChk).text('');--%>
<%--                emailChkBoolean = true;--%>
<%--            }--%>
<%--        });--%>

<%--        /**--%>
<%--         * 로그인 모달 form - 로그인 버튼--%>
<%--         * 1. 누락된 데이터 체크--%>
<%--         * 2. 로그인 성공 (data) 시 /main redirect--%>
<%--         * 3. 로그인 실패 (data === null) 시 alert--%>
<%--         */--%>
<%--        let loginSubmit = document.getElementById("login-submit");--%>
<%--        $(loginSubmit).click(function () {--%>
<%--            $.ajax({--%>
<%--                url: "${contextPath}/member/login",--%>
<%--                type: "post",--%>
<%--                dataType: "json",--%>
<%--                data: {--%>
<%--                    "email": $(loginEmail).val(),--%>
<%--                    "pwd": $(loginPwd).val(),--%>
<%--                },--%>
<%--                success: function (data) {--%>
<%--                    if (data === null) { // 실패--%>
<%--                        alert("이메일 또는 비밀번호를 확인해주세요.");--%>
<%--                    } if (data === 1) { // 정지된 회원--%>
<%--                        alert("정지된 회원 입니다.");--%>
<%--                    } else if (data) { // 성공--%>
<%--                        $(loginSubmit).attr("value", "Y");--%>
<%--                        alert("환영합니다.");--%>
<%--                        self.location = "${contextPath}/main";--%>
<%--                    }--%>
<%--                }--%>
<%--            });--%>
<%--        }); // 로그인 버튼 끝--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>
