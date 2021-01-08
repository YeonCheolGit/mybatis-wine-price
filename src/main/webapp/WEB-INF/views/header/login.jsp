<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /><html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        #modal_login {
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
        #modal_login:hover {
            color: white;
            background-color: lightsalmon;
        }
        #modal_signUp {
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
        #modal_signUp:hover {
            color: white;
            background-color: lightsalmon;
        }
    </style>
</head>
<body>
<!-- Button trigger modal -->
<button id="modal_login" type="button" class="btn btn-primary" data-bs-toggle="modal" >로그인</button>
<button id="modal_signUp" type="button" class="btn btn-primary" data-bs-toggle="modal" >회원가입</button>

<!-- Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">와인 검색 사이트</h5>
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
</body>
</html>
