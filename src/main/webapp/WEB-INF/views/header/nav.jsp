<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <form class="container-fluid justify-content-start">
        <button class="btn btn-outline-success me-2" type="button">
            <a class="navbar-brand" href="${contextPath}/wine/listPaging">목록</a>
        </button>
        <button class="btn btn-outline-success me-2" type="button">
            <c:if test="${member != null}"><a href="${contextPath}/member/logout">로그아웃</a></c:if>
            <c:if test="${member == null}"><a href="${contextPath}/member/loginForm">로그인</a></c:if>
        </button>
        <c:if test="${member == null}">
            <button class="btn btn-outline-success me-2" type="button">
                <a href="${contextPath}/member/goRegisterMemberForm">회원가입</a>
            </button>
        </c:if>
        <c:if test="${member != null}">
            <p>${member.id}님 안녕하세요.</p>
        </c:if>
    </form>
</nav>
</body>
</html>
