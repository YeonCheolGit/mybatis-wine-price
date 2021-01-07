<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>\</title>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".cancel").on("click", function(){
                location.href = "/";
            })

            $("#submit").on("click", function(){
                if($("#id").val()==""){
                    alert("아이디를 입력해주세요.");
                    $("#id").focus();
                    return false;
                }
                if($("#pwd").val()==""){
                    alert("비밀번호를 입력해주세요.");
                    $("#pwd").focus();
                    return false;
                }
                if($("#name").val()==""){
                    alert("성명을 입력해주세요.");
                    $("#name").focus();
                    return false;
                }
            });



        })
    </script>
</head>
<body>
    <section id="container">
        <form method="post" action="${contextPath}/member/registerMember">
            <div>
                <label for="id">아이디</label>
                <input type="email" id="id" name="id" />
            </div>
            <div>
                <label for="pwd">비밀번호</label>
                <input type="password" id="pwd" name="pwd" />
            </div>
            <div>
                <label for="name">성명</label>
                <input type="text" id="name" name="name" />
            </div>
            <div>
                <button type="submit" id="submit">회원가입</button>
                <button class="cancel" type="button">취소</button>
            </div>
        </form>
    </section>
</body>
</html>
