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
    <style type="text/css">
        li {list-style: none; float: left; padding: 6px;}
    </style>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>sweetness</th>
            <th>food</th>
            <th>acid</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allWineList}" var="allWineList">
            <tr>
                <td><a href="${contextPath}/wine/readOneWine?number=${allWineList.number}">${allWineList.name}</a></td>
                <td>${allWineList.sweetness}</td>
                <td>${allWineList.food}</td>
                <td>${allWineList.acid}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="box-footer">
    <div class="text-center">
        <ul class="pagination">
            <c:if test="${pageMaker.prev}">
                <li><a href="${contextPath}/wine/allWineList?page=${pageMaker.startPage - 1}">이전</a></li>
            </c:if>
            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                <li <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}"/>>
                    <a href="${contextPath}/wine/allWineList?page=${idx}">${idx}</a>
                </li>
            </c:forEach>
            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                <li><a href="${contextPath}/wine/allWineList?page=${pageMaker.endPage + 1}">다음</a></li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
