<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <style>
        #table_content {
            margin-left: 400px; margin-right: 400px; margin-top: 50px;
        }
        td, tr {
            text-align: center;
        }
        #searchBtn {
            margin-left: 8px; font-weight: bold;
        }
        .w-40 {
            width: 40% !important;
        }
        nav li.active a{
            color: coral; font-weight: bold;
        }
        @media (max-width: 1100px) {
            #pagination_buttons {
                display: flex;
                flex-direction: column;
                position: relative;
                float: none;
                width: auto;
            }
            #table_content {
                margin: auto;
            }
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="../commons/header.jsp" />
</header>
<form role="form" method="get">
    <div id="table_content">
        <table class="table table-hover" id="table-hover">
            <thead>
            <tr>
                <th>와인</th>
                <th>가격<i class="bi bi-sort-down-alt" id="price" style="margin-right: 3px"></i></th>
                <th>어울리는 음식</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allWineList}" var="allWineList">
                <tr>
                    <td><a href="#" onclick="window.open('${allWineList.URL}' + '${allWineList.name}')">${allWineList.name}</a></td>
                    <td>${allWineList.price}</td>
                    <td>${allWineList.food}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center" id="pagination_buttons">
        <nav>
            <ul class="pagination justify-content-center" id="pagination">
                <c:if test="${pageMaker.prev}">
                    <li class="page-item">
                        <c:if test="${orderByPrice == true}"> <%-- 가격 정렬을 했을 때 --%>
                            <a class="page-link" href="${contextPath}/wine/orderByPrice${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
                        </c:if>
                        <c:if test="${orderByPrice == null}"> <%-- 가격 정렬을 안했을 때 --%>
                            <a class="page-link" href="${contextPath}/wine/searchBarAndPagination${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
                        </c:if>
                    </li>
                </c:if>
                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                    <li aria-current="page" <c:out value="${pageMaker.cri.page == idx? 'class=active':''}"/>>
                        <c:if test="${orderByPrice == true}"> <%-- 가격 정렬을 했을 때 --%>
                            <a class="page-link" href="${contextPath}/wine/orderByPrice${pageMaker.makeSearch(idx)}">${idx}</a> <%-- 페이지 번호 --%>
                        </c:if>
                        <c:if test="${orderByPrice == null}"> <%-- 가격 정렬을 안했을 때 --%>
                            <a class="page-link" href="${contextPath}/wine/searchBarAndPagination${pageMaker.makeSearch(idx)}">${idx}</a> <%-- 페이지 번호 --%>
                        </c:if>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                    <li class="page-item">
                        <c:if test="${orderByPrice == true}"> <%-- 가격 정렬을 했을 때 --%>
                            <a class="page-link" href="${contextPath}/wine/orderByPrice${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a>
                        </c:if>
                        <c:if test="${orderByPrice == null}"> <%-- 가격 정렬을 안했을 때 --%>
                            <a class="page-link" href="${contextPath}/wine/searchBarAndPagination${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a>
                        </c:if>
                    </li>
                </c:if>
            </ul>
        </nav>
        <div class="container w-40">
            <div class="d-flex align-items-center">
                <label for="searchType">
                    <select class="form-select" name="searchType" id="searchType">
                        <option value="n" <c:out value="${searchCriteria.searchType == null ? 'selected' : ''}" />>선택</option>
                        <option value="t" <c:out value="${searchCriteria.searchType eq 't' ? 'selected' : ''}" />>와인</option>
                        <option value="c" <c:out value="${searchCriteria.searchType eq 'c' ? 'selected' : ''}" />>가격</option>
                        <option value="w" <c:out value="${searchCriteria.searchType eq 'w' ? 'selected' : ''}" />>어울리는 음식</option>
                    </select>
                </label>
                <form class="d-inline">
                    <input class="d-inline form-control" id="keywordInput" name="keywords" type="text"
                           value="${searchCriteria.keyword}" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success me-2 flex-shrink-0" id="searchBtn" type="button">검색</button>
                </form>
            </div>
        </div>
    </div>
</form>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#searchBtn').click(function() { // 검색 창 검색 버튼 클릭 시 동작
            self.location = "${contextPath}/wine/searchBarAndPagination${pageMaker.makeQuery(1)}"
                + "&searchType=" + $("select option:selected").val()
                + "&keyword=" + encodeURIComponent($("#keywordInput").val());

        });
        $('#price').click(function() { // 정렬 버튼 클릭 시 동작
            self.location = "${contextPath}/wine/orderByPrice${pageMaker.makeQuery(1)}"
                + "&orderByPrice=" + $("select option:selected").val()
                + "&keyword=" + encodeURIComponent($("#keywordInput").val());
        });
        let cache = {}; // 검색창에 입력한 글자를 저장하는 캐시
        $( "#keywordInput" ).autocomplete ({ // 검색 창에 두글자 입력부터 동작
            minLength: 2,
            source: function( request, response ) {
                let term = request.term;
                if (term in cache) {
                    response(cache[term]);
                    return;
                }
                $.getJSON("${contextPath}/wine/autocomplete", request, function (data, status, xhr) {
                    cache[term] = data;
                    response(data);
                });
            }
        });
    });
</script>
</body>
</html>

