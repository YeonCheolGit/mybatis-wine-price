<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<html>
<head>
    <div>
        <jsp:include page="../header/nav.jsp" />
    </div>
    <title></title>
</head>
<body>
<div>
    <table>
        <thead>
            <tr>
                <th>이름</th>
                <th>생산지</th>
                <th>종류</th>
                <th>도수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allWineList}" var="wineList">
                <tr>
                    <td><a href="${contextPath}/wine/readOneWine?number=${wineList.number}">${wineList.name}</a></td>
                    <td>${wineList.origin}</td>
                    <td>${wineList.type}</td>
                    <td>${wineList.alcohol}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
