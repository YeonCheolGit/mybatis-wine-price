<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<head>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#logoutBtn").on("click", function(){
                location.href="${contextPath}/member/logout";
            })
        })
    </script>
    <title>Title</title>
</head>
<body>
<div class="dropdown-menu">
    <form class="px-4 py-3" name='loginForm' method="post" action="${contextPath}/member/login">
        <div class="form-group">
            <label for="id">Email address</label>
            <input class="form-control" type="text" id="id" placeholder="email@example.com">
        </div>
        <div class="form-group">
            <label for="pwd">Password</label>
            <input type="password" class="form-control" id="pwd" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="${contextPath}/member/goRegisterMemberForm">New around here? Sign up</a>
    <a class="dropdown-item" href="#">Forgot password?</a>
</div>
</body>
</html>
