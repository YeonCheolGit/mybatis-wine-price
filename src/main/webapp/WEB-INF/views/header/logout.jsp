<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>
<style>
    #modal_logout {
    margin-left: 6px;
    border: 1px solid lightsalmon;
    background-color: rgba(0,0,0,0);
    color: lightsalmon;
    padding-top: 5px;
    padding-bottom: 5px;
    text-align: center;
    -webkit-text-size-adjust: auto;
    font-weight: bold;
    }
    #modal_logout:hover {
    color: white;
    background-color: lightsalmon;
    }
</style>
</head>
<body>
    <a href="${contextPath}/member/logout">
        <button class="btn btn-outline-success me-2" type="button" id="modal_logout">로그아웃</button>
    </a>
</body>
</html>
