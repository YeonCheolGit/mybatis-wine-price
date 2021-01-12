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
    <style>
        #modal_login,
        #modal_logout,
        #modal_register {
            margin-left: 2px;
            border: 1px solid lightsalmon;
            background-color: rgba(0,0,0,0);
            color: lightsalmon;
            padding-top: 5px;
            padding-bottom: 5px;
            text-align: center;
            -webkit-text-size-adjust: auto;
            font-weight: bold;
        }
        #modal_login:hover,
        #modal_register:hover {
            color: white;
            background-color: lightsalmon;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#modal_login').click(function () {
                $('#loginModal').modal("show");
            });
            $('#modal_register').click(function () {
                $('#registerModal').modal("show");
            });
            $('#close_modal').click(function () {
                $('#exampleModal').modal("hide");
            });

            $("#submit").on("click", function(){
                if($("#id").val()===""){
                    alert("아이디를 입력해주세요.");
                    $("#userId").focus();
                    return false;
                }
                if($("#pwd").val()===""){
                    alert("비밀번호를 입력해주세요.");
                    $("#userPass").focus();
                    return false;
                }
                if($("#name").val()===""){
                    alert("성명을 입력해주세요.");
                    $("#userName").focus();
                    return false;
                }
            });

        })
    </script>
</head>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <c:if test="${member == null}">
                        <a href="${contextPath}/wine/allWineList">
                            <button class="btn btn-outline-success me-2" type="button">목록</button>
                        </a>
                        <button id="modal_login" type="button" class="btn btn-primary" data-bs-toggle="modal">로그인</button>
                        <button id="modal_register" type="button" class="btn btn-primary" data-bs-toggle="modal">회원가입</button>
                    </c:if>
                    <c:if test="${member != null}">
                        <a href="${contextPath}/wine/allWineList">
                            <button class="btn btn-outline-success me-2" type="button">목록</button>
                        </a>
                        <a href="${contextPath}/member/logout">
                            <button class="btn btn-outline-success me-2" type="button" id="modal_logout">로그아웃</button>
                        </a>
                        <p>${member.name}님 안녕하세요.</p>
                    </c:if>
                </div>
            </div>
        </div>
        <form class="container-fluid justify-content-start" method="post" action="${contextPath}/member/login">
            <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="loginModalLabel">로그인 창</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="id" class="form-label">아이디</label>
                                <input type="email" id="id" name="id" class="form-control" placeholder="example@email.com">
                            </div>
                            <div class="mb-3">
                                <label for="pwd" class="form-label">비밀번호</label>
                                <input type="password" id="pwd" name="pwd" class="form-control">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                            <button type="submit" class="btn btn-primary" id="submit">로그인</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <form class="container-fluid justify-content-start" method="post" action="${contextPath}/member/registerMember" name="registerForm">
            <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="registerModalLabel">와인 검색 사이트</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="id" class="form-label">아이디</label>
                                <input type="email" id="id" name="id" class="form-control" placeholder="example@email.com">
                            </div>
                            <div class="mb-3">
                                <label for="pwd" class="form-label">비밀번호</label>
                                <input type="password" id="pwd" name="pwd" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label">이름</label>
                                <input type="text" id="name" name="name" class="form-control">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                            <button type="submit" class="btn btn-primary" id="submit">회원가입</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </nav>
</div>
</body>
</html>
