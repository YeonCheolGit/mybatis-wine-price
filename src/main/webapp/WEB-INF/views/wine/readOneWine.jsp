<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title></title>
    <jsp:include page="../commons/header.jsp" />
</head>
<body>
    <div>
        <table>
            <thead>
            <tr>
                <th></th>
                <th>이름</th>
                <th>어울리는 음식</th>
                <th>가격</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${readOneWine.number}</td>
                <td>${readOneWine.name}<td>
                <td>${readOneWine.food}</td>
                <td>${readOneWine.price}</td>
            </tr>
            </tbody>
        </table>

    </div>
</body>
</html>
