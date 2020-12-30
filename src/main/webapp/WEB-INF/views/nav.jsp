<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <ul>
        <li><a href="${contextPath}/wine/allWineList">목</a></li>
        <li>
            <c:if test="${member != null}"><a href="${contextPath}/member/logout">로그아웃</a></c:if>
            <c:if test="${member == null}"><a href="${contextPath}/member/loginForm">로그인</a></c:if>
        </li>
        <li>
            <c:if test="${member != null}">
                <p>${member.id}님 안녕하세요.</p>
            </c:if>
        </li>
    </ul>
</body>
</html>
