<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <form class="container-fluid justify-content-start" method="post" action="${contextPath}/member/login">
        <a class="navbar-brand" href="${contextPath}/wine/allWineList">
            <button class="btn btn-outline-success me-2" type="button">목록</button>
        </a>
        <c:if test="${member != null}">
            <a href="${contextPath}/member/logout">
                <button class="btn btn-outline-success me-2" type="button">로그아웃</button>
            </a>
        </c:if>
        <!-- Button trigger modal -->
        <c:if test="${member == null}">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" id="modal_show">로그인</button>
        <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">와인 검색 사이트</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="id" class="form-label">아이디</label>
                                <input type="email" id="id" name="id" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="pwd" class="form-label">비밀번호</label>
                                <input type="password" id="pwd" name="pwd" class="form-control">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                            <button type="submit" class="btn btn-primary">로그인</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <%-- login message --%>
        <c:if test="${member != null}">
            <p>${member.name}님 안녕하세요.</p>
        </c:if>
    </form>
</nav>
<script>
    $(document).ready(function () {
        $('#modal_show').click(function () {
            $('#exampleModal').modal("show");
        });
        $('#close_modal').click(function () {
            $('#exampleModal').modal("hide");
        });
    })
</script>
</body>
</html>
