<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>비밀번호 찾기 모달</title>
</head>
<body>
<%-- 비밀번호 찾기 모달 --%>
<div class="modal fade" id="find-pw-modal-button" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content-findPw">
            <div class="modal-header">
                <h5 class="modal-title" id="findPwModalLabel">비밀번호 찾기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="find-pw-email" class="form-label">이메일</label>
                    <input type="email" id="find-pw-email" name="email" class="form-control" placeholder="example@email.com">
                </div>
                <div class="mb-3">
                    <label for="find-pw-nickName" class="form-label">아이디</label>
                    <input type="text" id="find-pw-nickName" name="nickName" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="send-new-pwd">비밀번호 찾기</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
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
                url: "${contextPath}/member/findPwd",
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
                        self.location = "${contextPath}/main/errorPage";
                    } else { // nickName && email 일치
                        alert("임시 비밀번호를 발송 했습니다");
                        self.location = "${contextPath}/main";
                    }
                }
            });
        }); // 새 비밀번호 전송 버튼 끝
    });
</script>
</body>
</html>
