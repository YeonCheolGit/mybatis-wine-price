<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="table_content">
    <table class="table table-hover" id="recommend-table-hover">
        <thead>
            <tr>
                <th>이메일</th>
                <th>아이디</th>
                <th>가입 날짜</th>
                <th>정지 여부</th>
            </tr>
        </thead>
        <tbody>
        <div>
            <c:forEach items="${allMemberList}" var="allMemberList">
                <tr>
                    <td>
                        <label for="email"></label>
                        <input id="email" value="${allMemberList.username}" readonly="readonly">
                    </td>
                    <td>${allMemberList.nickName}</td>
                    <td>${allMemberList.regDate}</td>
                    <c:if test="${allMemberList.enabled == 1}"> <%-- 계정 활성화 중이라면 --%>
                        <td>활동 중</td>
                        <td><button id="enabledPause" type="button">활동 정지 시키기</button></td>
                    </c:if>
                    <c:if test="${allMemberList.enabled == 0}"> <%-- 계정 정지 중이라면 --%>
                        <td>활동 정지 됨</td>
                        <td><button id="enabledActive" type="button">활동 정지 해제</button></td>
                    </c:if>
                </tr>
            </c:forEach>
        </div>
        </tbody>
    </table>
    <button type="submit"><a href="${contextPath}/main/">첫 화면으로 이동하시겠습니까?</a></button>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/admin.js"></script>
<%--<script type="text/javascript">--%>
<%--    $(document).ready(function () {--%>
<%--        /*--%>
<%--         * 계정 정지 버튼--%>
<%--         */--%>
<%--        $('#enabledPause').click(function () {--%>
<%--                $.ajax({--%>
<%--                    url: "${contextPath}/admin/enabledPause",--%>
<%--                    type: "post",--%>
<%--                    dataType: "json",--%>
<%--                    data: {--%>
<%--                        "email": $("#email").val(),--%>
<%--                    },--%>
<%--                    success: function (data) {--%>
<%--                        if (data) {--%>
<%--                            alert("계정 정지 처리 되었습니다.");--%>
<%--                            self.location = "${contextPath}/admin/adminPage.do";--%>
<%--                        } else {--%>
<%--                            alert("알 수 없는 에러")--%>
<%--                        }--%>
<%--                    }--%>
<%--                });--%>
<%--        }); // 계정 정지 버튼 끝--%>

<%--        /*--%>
<%--         * 계정 활성화 버튼--%>
<%--         */--%>
<%--        $('#enabledActive').click(function () {--%>
<%--            $.ajax({--%>
<%--                url: "${contextPath}/admin/enabledActive",--%>
<%--                type: "post",--%>
<%--                dataType: "json",--%>
<%--                data: {--%>
<%--                    "email": $("#email").val(),--%>
<%--                },--%>
<%--                success: function (data) {--%>
<%--                    if (data) {--%>
<%--                        alert("계정 활성화 되었습니다.");--%>
<%--                        self.location = "${contextPath}/admin/adminPage.do";--%>
<%--                    } else {--%>
<%--                        alert("알 수 없는 에러")--%>
<%--                    }--%>
<%--                }--%>
<%--            });--%>
<%--        }); // 계정 활성화 버튼 끝--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>
