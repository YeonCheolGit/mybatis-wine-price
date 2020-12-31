<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<script type="text/javascript">
    $(document).ready(function(){
        $("#logoutBtn").on("click", function(){
            location.href="${contextPath}/member/logout";
        })
    })
</script>
<body>
    <form name='homeForm' method="post" action="${contextPath}/member/login">
        <c:if test="${member == null}">
            <div>
                <label for="id"></label>
                <input type="text" id="id" name="id">
            </div>
            <div>
                <label for="pwd"></label>
                <input type="password" id="pwd" name="pwd">
            </div>
            <div>
                <button type="submit">로그인</button>
                <button type="button">회원가입</button>
            </div>
        </c:if>
        <c:if test="${member != null }">
            <ul>
                <li><a href="${contextPath}/wine/allWineList">와인 목록</a></li>
            </ul>
            <div>
                <p>${member.id}님 환영 합니다.</p>
                <button id="logoutBtn" type="button">로그아웃</button>
            </div>
        </c:if>
        <c:if test="${msg == false}">
            <p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
        </c:if>
    </form>
</body>
</html>
