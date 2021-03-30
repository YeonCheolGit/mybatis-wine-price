<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>회원정보 수정 모달</title>
</head>
<body>
<%-- 회원정보 업데이트 모달 --%>
<div class="modal fade" id="updateMember_modal_form" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content-update">
            <div class="modal-header">
                <h5 class="modal-title" id="updateMemberLabel">와인 검색 사이트</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="update_email" class="form-label">이메일</label>
                    <input type="email" id="update_email" name="email" class="form-control" value="${member.email}" readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="update_pwd" class="form-label">비밀번호</label>
                    <input type="password" id="update_pwd" name="pwd" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="update_nickName" class="form-label">아이디</label>
                    <input type="text" id="update_nickName" name="nickName" class="form-control" value="${member.nickName}" readonly="readonly">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="submit" class="btn btn-primary" id="update_submit">회원정보 수정</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        /**
         * 회원수정 모달 form - 수정 버튼
         * 1. 누락된 비밀번호 체크
         * 2. 비밀번호 변경 후 /main redirect
         */
        $('#update_submit').click(function () {
            $.ajax({
                url: "${contextPath}/member/updateMember",
                type: "post",
                dataType: "json",
                data: {
                    "email": $("#update_email").val(),
                    "pwd": $("#update_pwd").val(),
                    "nickName": $("#update_nickName").val(),
                },
                success: function (data) { // 회원정보 수정 후 return
                    if (data === null) {
                        alert("비밀번호를 확인해주세요.");
                    } else if (data === true) {
                        alert("회원정보 수정이 완료 됐습니다.");
                        self.location = "${contextPath}/main";
                    }
                }
            });
        });
    });
</script>
</body>
</html>