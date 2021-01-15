<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<html>
<head>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<header>
    <jsp:include page="../commons/header.jsp" />
</header>
<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>와인</th>
            <th>당도</th>
            <th>산도</th>
            <th>어울리는 음식</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allWineList}" var="allWineList">
            <tr>
                <td><a href="${contextPath}/wine/readOneWine?number=${allWineList.number}">${allWineList.name}</a></td>
                <td>${allWineList.sweetness}</td>
                <td>${allWineList.acid}</td>
                <td>${allWineList.food}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="box-footer">
    <div class="text-center">
        <nav>
            <ul class="pagination justify-content-center">
                <c:if test="${pageMaker.prev}">
                    <li class="page-item">
                        <a class="page-link" href="${contextPath}/wine/allWineList?page=${pageMaker.startPage - 1}">이전</a>
                    </li>
                </c:if>
                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                    <li class="page-item" aria-current="page" <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}"/>>
                        <a class="page-link" href="${contextPath}/wine/allWineList?page=${idx}">${idx}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                    <li class="page-item">
                        <a class="page-link" href="${contextPath}/wine/allWineList?page=${pageMaker.endPage + 1}">다음</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
