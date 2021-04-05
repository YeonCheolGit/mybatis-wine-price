<%--suppress JSJQueryEfficiency --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<link>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style>
        #login_modal_button, #modal_logout, #register_modal_button, #update_modal_button, #page_admin, #chatButton {
            margin-left: 2px;
            border: 1px solid lightsalmon;
            background-color: rgba(0, 0, 0, 0); color: lightsalmon;
            padding-top: 5px; padding-bottom: 5px;
            text-align: center;
            -webkit-text-size-adjust: auto;
            font-weight: bold;
            position: relative;
        }
        #findPw_modal_button { background-color: dodgerblue; }
        #login_modal_button:hover, #register_modal_button:hover { color: white; background-color: lightsalmon; }
        #emailChk {
            margin-top: 1px;
            padding-top: 1px; padding-bottom: 1px;
            color: lightcoral; font-weight: bold;
        }
        #emailChk:hover{ color: white; }
        #dark_mode_button {
            border: 1px solid black;
            background-color: white;
            color: black;
            font-weight: bold;
        }
        #dark-mode-div {
            margin-left: 90%; position: absolute;
        }
        html {
            --bg-color: #fff;
            --text-color: black;
            --link-color: #0000ee;
        }
        <%-- 다크 모드 동작 시 글자 색 변경 --%>
        html.dark {
            --bg-color: #121212;
            --text-color: white;
            --link-color: #3ea6ff;
        }
        body { background: var(--bg-color); color: var(--text-color); }
        a, th, td { color: var(--text-color); }
        #modal-content-login, #modal-content-register, #modal-content-update { background: var(--bg-color); color: var(--text-color); }
        #text { color: black; }
        #pwd_validation_chk, #register_email_validation_chk, #login_email_validation_chk { color: dodgerblue; font-weight: bold; }

        <%-- 화면 사이즈 조정 시 헤더 크기 조정 --%>
        @media (max-width: 1100px) {
            #navbarNavAltMarkup {
                display: flex;
                flex-direction: column;
                position: relative;
                float: left;
                width: auto;
            }
            #navbar-nav {
                float: left;
                display: flex;
                flex-direction: column;
            }
            #dark-mode-div {
                display: flex;
                flex-direction: column;
                float: right;
                position: absolute;
            }
        }
    </style>
</head>
<body>
<div>
    <nav id="navbar" class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
        <div id="container-xxl" class="container-xxl" style="margin-left: 5px">
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div id="navbar-nav" class="navbar-nav" style="width: auto">
                    <button class="btn btn-outline-success me-2" type="button"
                            onclick="location.href='${contextPath}/wine/searchBarAndPagination'">와인목록</button>
                    <%--@elvariable id="member" type="main.DTO.MemberDTO"--%>
                    <c:if test="${member == null}"> <%-- 미 로그인 시 보여질 버튼--%>
                        <button class="btn btn-primary" data-bs-toggle="modal" id="login_modal_button" type="button" >로그인</button>
                        <button class="btn btn-primary" data-bs-toggle="modal" id="register_modal_button" type="button" >회원가입</button>
                    </c:if>
                    <c:if test="${member != null}"> <%-- 로그인 시 보여질 버튼 --%>
                        <button class="btn btn-primary" type="button" id="modal_logout"
                                onclick="location.href='${contextPath}/member/logout'">로그아웃</button>
                        <button class="btn btn-primary" id="update_modal_button" type="button">마이페이지</button>
                        <button class="btn btn-primary" type="button" id="chatButton"
                                onclick="location.href='${contextPath}/main/kafkaPort'">채팅방</button>
                        <%--@elvariable id="admin_session" type="main.DTO.MemberDTO"--%>
                        <c:if test="${admin_session != null}"> <%-- 관리자 로그인 시 보여질 버튼 --%>
                            <button class="btn btn-primary" id="page_admin" type="button">관리자권한</button>
                            <span class="navbar-text" style="margin-left: 4px">관리자 계정 입니다</span>
                        </c:if>
                        <c:if test="${admin_session == null}"> <%-- 일반 유저 로그인 시 보여질 버튼 --%>
                            <span class="navbar-text" style="margin-left: 4px">${member.nickName}님 안녕하세요.</span>
                        </c:if>
                    </c:if>

                </div>
            </div>
            <div id="dark-mode-div" class="dark-mode-div">
                <button id="dark_mode_button" class="btn btn-primary">다크모드</button>
            </div>
        </div>
    </nav>
</div>
<jsp:include page="../member_modal_form/login_modal_form.jsp" />
<jsp:include page="../member_modal_form/findPw_modal_form.jsp" />
<jsp:include page="../member_modal_form/register_modal_form.jsp" />
<jsp:include page="../member_modal_form/updateMember_modal_form.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        document.getElementById("dark_mode_button").addEventListener("click", () => { // 다크모드 버튼 클릭 시 리스너
            const html = document.documentElement;
            if (html.classList.contains("dark")) { // 현재 다크 모드가 켜져있을 때
                html.classList.remove("dark");
                localStorage.setItem("darkTheme", "false");
                $("#dark_mode_button").text("다크모드 켜기");
            } else { // 다크모드 꺼진 상태
                html.classList.add("dark");
                localStorage.setItem("darkTheme", "true");
                $("#dark_mode_button").text("다크모드 끄기");
            }
        });
        const storedTheme = localStorage.getItem("darkTheme");
        // 세션 체크 후 다크모드 설정 했었으면, 버튼 누르지 않아도 자동 다크모드
        if (storedTheme !== null) {
            if (storedTheme === "true") {
                document.documentElement.classList.add("dark");
                $("#dark_mode_button").text("다크모드 끄기");
            }
        }

        // 로그인, 회원가입, 회원수정, 비밀번호 찾기 모달 form 버튼
        $('#login_modal_button').click(function () {
            $('#login_modal_form').modal("show");
        });
        $('#register_modal_button').click(function () {
            $('#register_modal_form').modal("show");
        });
        $('#update_modal_button').click(function () {
            $('#updateMember_modal_form').modal("show")
        });
        $('#close_modal').click(function () {
            $('#exampleModal').modal("hide");
        });
        // Admin 페이지 버튼 클릭 시
        $('#page_admin').click(function () {
            location.href = "${contextPath}/admin/adminPage.do";
        });
    });
</script>
</body>
</html>