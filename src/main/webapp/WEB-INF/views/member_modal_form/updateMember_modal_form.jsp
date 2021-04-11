<%--@elvariable id="member" type="main.DTO.MemberDTO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>회원정보 수정 모달</title>
</head>
<body>
<%-- 회원정보 업데이트 모달 --%>
<div class="modal fade" id="updateMember-modal-form" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content-update">
            <div class="modal-header">
                <h5 class="modal-title" id="updateMemberLabel">와인 검색 사이트</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="update-email" class="form-label">이메일</label>
                    <input type="email" id="update-email" name="email" class="form-control" value="${member.email}" readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="update-pwd" class="form-label">비밀번호</label>
                    <input type="password" id="update-pwd" name="pwd" class="form-control">
                    <div id="update-pwd-validation-chk"></div>
                </div>
                <div class="mb-3">
                    <label for="update-nickname" class="form-label">아이디</label>
                    <input type="text" id="update-nickname" name="nickName" class="form-control" value="${member.nickName}" readonly="readonly">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="submit" class="btn btn-primary" id="update_submit">회원정보 수정</button>
            </div>
        </div>
    </div>
</div>
<script>let contextPath = "${pageContext.request.contextPath}"</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/updateMember.js"></script>
<%--<script type="text/javascript">--%>
<%--    $(document).ready(function () {--%>
<%--        let updateEmail = document.getElementById("update-email");--%>
<%--        let updatePwd = document.getElementById("update-pwd");--%>
<%--        let updateNickName = document.getElementById("update-nickname");--%>
<%--        let updatePwdValidChk = document.getElementById("update-pwd-validation-chk");--%>

<%--        /*--%>
<%--         * 비밀번호 규칙--%>
<%--         * - 최소 8글자--%>
<%--         * - 최소 1개의 소문자 && 대문자--%>
<%--         * - 최소 1개의 숫자--%>
<%--         * - 최소 1개의 특수문자--%>
<%--         */--%>
<%--        let pwdRegex = /^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\w!@#$%^&*]{8,}$/;--%>
<%--        let updatePwdChkBoolean = false;--%>
<%--        $('#update_pwd').on("propertychange change keyup paste input", function () { // 회원가입 비밀번호 입력 시 타이핑 마다 검사--%>
<%--            if (pwdRegex.test($('#update_pwd').val())) {--%>
<%--                $(updatePwdValidChk).text('비밀번호로 적합합니다.');--%>
<%--                updatePwdChkBoolean = true;--%>
<%--            } else {--%>
<%--                $(updatePwdValidChk).text('1개의 대문자 / 특수기호가 포함되어야 합니다.');--%>
<%--                updatePwdChkBoolean = false;--%>
<%--            }--%>
<%--        });--%>

<%--        /**--%>
<%--         * 회원수정 모달 form - 수정 버튼--%>
<%--         * 1. 누락된 비밀번호 체크--%>
<%--         * 2. 비밀번호 변경 후 /main redirect--%>
<%--         */--%>

<%--        $('#update_submit').click(function () {--%>
<%--            if (updatePwdChkBoolean === true) {--%>
<%--                $.ajax({--%>
<%--                    url: "${contextPath}/member/updateMember",--%>
<%--                    type: "post",--%>
<%--                    dataType: "json",--%>
<%--                    data: {--%>
<%--                        "email": $(updateEmail).val(),--%>
<%--                        "pwd": $(updatePwd).val(),--%>
<%--                        "nickName": $(updateNickName).val(),--%>
<%--                    },--%>
<%--                    success: function (data) { // 회원정보 수정 후 return--%>
<%--                        if (data === false) {--%>
<%--                            alert("비밀번호를 확인해주세요.");--%>
<%--                        } else if (data === true) {--%>
<%--                            alert("회원정보 수정이 완료 됐습니다.");--%>
<%--                            self.location = "${contextPath}/main";--%>
<%--                        }--%>
<%--                    }--%>
<%--                });--%>
<%--            } else {--%>
<%--                alert("유효한 비밀번호 양식이 아닙니다.");--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>