<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<html>
<head>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<header>
    <jsp:include page="../commons/header.jsp" />
</header>
<form role="form" method="get">
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
                            <a class="page-link" href="${contextPath}/wine/searchBar${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
                        </li>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                        <li class="page-item" aria-current="page" <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}"/>>
                            <a class="page-link" href="${contextPath}/wine/searchBar${pageMaker.makeSearch(idx)}">${idx}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                        <li class="page-item">
                            <a class="page-link" href="${contextPath}/wine/searchBar?${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            <div>
                <label for="searchType">
                    <select name="searchType" id="searchType">
                        <option value="n" <c:out value="${searchCriteria.searchType == null ? 'selected' : ''}" />>선택</option>
                        <option value="t" <c:out value="${searchCriteria.searchType eq 't' ? 'selected' : ''}" />>와인</option>
                        <option value="c" <c:out value="${searchCriteria.searchType eq 'c' ? 'selected' : ''}" />>당도</option>
                        <option value="w" <c:out value="${searchCriteria.searchType eq 'w' ? 'selected' : ''}" />>어울리는 음식</option>
                    </select>
                </label>
            </div>
            <div class="d-sm-inline-block" style="width: 10%;">
                <form class="d-inline">
                    <input class="d-inline form-control" id="keywordInput" name="keywords" type="text"
                           value="${searchCriteria.keyword}" placeholder="Search" aria-label="Search">
                    <button class="d-inline btn btn-outline-success" id="searchBtn" type="button">검색</button>
                </form>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function () {
        $('#searchBtn').click(function() {
            self.location = "${contextPath}/wine/searchBar${pageMaker.makeQuery(1)}"
                + "&searchType=" + $("select option:selected").val()
                + "&keyword=" + encodeURIComponent($("#keywordInput").val());
        });
        jQuery.noConflict();
        let cache = {};
        $( "#keywordInput" ).autocomplete ({
            minLength: 2,
            source: function( request, response ) {
                let term = request.term;
                if (term in cache) {
                    response(cache[term]);
                    return;
                }
                $.getJSON("${contextPath}/wine/search", request, function (data, status, xhr) {
                    cache[term] = data;
                    response(data);
                });
            }
        });
    });
</script>
</body>
</html>
