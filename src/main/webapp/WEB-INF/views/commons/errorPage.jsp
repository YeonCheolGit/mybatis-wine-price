<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>예기치 못 한 에러가 발생 했습니다.</p>
    <button type="button"><a href="${contextPath}/main">첫 화면으로 이동하시겠습니까?</a></button>
</body>
</html>
