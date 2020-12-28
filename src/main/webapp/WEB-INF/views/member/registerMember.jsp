<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <section id="container">
        <form method="post" action="${contextPath}/member/registerMember">
            <div>
                <label for="id">아이디</label>
                <input type="text" id="id" name="id" />
            </div>
            <div>
                <label for="pwd">비밀번호</label>
                <input type="password" id="pwd" name="pwd" />
            </div>
            <div>
                <label for="name">성명</label>
                <input type="text" id="name" name="name" />
            </div>
            <div>
                <button type="submit" id="submit">회원가입</button>
                <button type="button">취소</button>
            </div>
        </form>
    </section>
</body>
</html>
