<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<link>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <form class="container-fluid justify-content-start" method="post" action="${contextPath}/member/login">
        <jsp:include page="list.jsp" /> <%-- list button --%>

        <c:if test="${member != null}">
            <jsp:include page="logout.jsp" /> <%-- logout button--%>
            <p>${member.name}님 안녕하세요.</p> <%-- login messege --%>
        </c:if>

        <%-- login and signUp button --%>
        <c:if test="${member == null}">
            <jsp:include page="login.jsp" />
        </c:if>
    </form>
</nav>
<script>
    $(document).ready(function () {
        $('#modal_login').click(function () {
            $('#loginModal').modal("show");
        });
        $('#close_modal').click(function () {
            $('#exampleModal').modal("hide");
        });
    })
</script>
</body>
</html>
