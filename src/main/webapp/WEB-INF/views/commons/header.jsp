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
        #modal_login, #modal_logout, #modal_register, #modal_update, #page_admin {
            margin-left: 2px;
            border: 1px solid lightsalmon;
            background-color: rgba(0, 0, 0, 0); color: lightsalmon;
            padding-top: 5px; padding-bottom: 5px;
            text-align: center;
            -webkit-text-size-adjust: auto;
            font-weight: bold;
            position: relative;
        }
        #modal_findPw {
            background-color: dodgerblue;
        }
        #modal_login:hover, #modal_register:hover {
            color: white; background-color: lightsalmon;
        }
        #emailChk {
            margin-top: 1px;
            padding-top: 1px; padding-bottom: 1px;
            color: lightcoral; font-weight: bold;
        }
        #emailChk:hover{
            color: white;
        }
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
        body {
            background: var(--bg-color);
            color: var(--text-color);
        }
        a, th, td {
            color: var(--text-color);
        }
        #modal-content-login, #modal-content-register, #modal-content-update {
            background: var(--bg-color);
            color: var(--text-color);
        }
        #text {
            color: black;
        }
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
                        <button class="btn btn-primary" data-bs-toggle="modal" id="modal_login" type="button" >로그인</button>
                        <button class="btn btn-primary" data-bs-toggle="modal" id="modal_register" type="button" >회원가입</button>
                    </c:if>
                    <c:if test="${member != null}"> <%-- 로그인 시 보여질 버튼 --%>
                        <button class="btn btn-primary" type="button" id="modal_logout"
                                onclick="location.href='${contextPath}/member/logout'">로그아웃</button>
                        <button class="btn btn-primary" id="modal_update" type="button">마이페이지</button>
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
        <%-- 로그인 모달--%>
        <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="modal-content-login">
                    <div class="modal-header">
                        <h5 class="modal-title" id="loginModalLabel">로그인 창</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="login_email" class="form-label">이메일</label>
                            <input type="email" id="login_email" name="email" class="form-control" placeholder="example@email.com">
                        </div>
                        <div class="mb-3">
                            <label for="login_pwd" class="form-label">비밀번호</label>
                            <input type="password" id="login_pwd" name="pwd" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary" id="login_submit" value="N">로그인</button>
                        <button type="button" class="btn btn-primary" id="modal_findPw">비밀번호 찾기</button>
                    </div>
                </div>
            </div>
        </div>
        <%-- 비밀번호 찾기 모달 --%>
        <div class="modal fade" id="findPwModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="modal-content-findPw">
                    <div class="modal-header">
                        <h5 class="modal-title" id="findPwModalLabel">비밀번호 찾기</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="findPw_email" class="form-label">이메일</label>
                            <input type="email" id="findPw_email" name="email" class="form-control" placeholder="example@email.com">
                        </div>
                        <div class="mb-3">
                            <label for="findPw_nickName" class="form-label">아이디</label>
                            <input type="text" id="findPw_nickName" name="nickName" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="sendNewPwd">비밀번호 찾기</button>
                    </div>
                </div>
            </div>
        </div>
        <%-- 회원가입 모달 --%>
        <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="modal-content-register">
                    <div class="modal-header">
                        <h5 class="modal-title" id="registerModalLabel">와인 검색 사이트</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="register_email" class="form-label">이메일</label>
                            <input type="email" id="register_email" name="email" class="form-control" placeholder="example@email.com">
                            <button type="button" class="btn btn-outline-warning" id="emailChk" value="N">중복확인</button>
                        </div>
                        <div class="mb-3">
                            <label for="register_pwd" class="form-label">비밀번호</label>
                            <input type="password" id="register_pwd" name="pwd" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="register_nickName" class="form-label">아이디</label>
                            <input type="text" id="register_nickName" name="nickName" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                        <button type="submit" class="btn btn-primary" id="register_submit">회원가입</button>
                    </div>
                </div>
            </div>
        </div>
        <%-- 회원정보 업데이트 모달 --%>
        <div class="modal fade" id="updateMemberModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="modal-content-update">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateMemberLabel">와인 검색 사이트</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="update_email" class="form-label">이메일</label>
                            <input type="email" id="update_email" name="email" class="form-control" value="${member.email}" readonly="readonly">
                        </div>
                        <div class="mb-3">
                            <label for="update_pwd" class="form-label">비밀번호</label>
                            <input type="password" id="update_pwd" name="pwd" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="update_nickName" class="form-label">아이디</label>
                            <input type="text" id="update_nickName" name="nickName" class="form-control" value="${member.nickName}" readonly="readonly">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                        <button type="submit" class="btn btn-primary" id="update_submit">회원정보 수정</button>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
        // 로그인, 회원가입, 회원수정, 비밀번호 찾기 modal form
        $('#modal_login').click(function () {
            $('#loginModal').modal("show");
        });
        $('#modal_register').click(function () {
            $('#registerModal').modal("show");
        });
        $('#modal_update').click(function () {
            $('#updateMemberModal').modal("show")
        });
        $('#modal_findPw').click(function () {
            $('#findPwModal').modal("show")
        });
        $('#close_modal').click(function () {
            $('#exampleModal').modal("hide");
        });
        // 회원가입 시 미입력 정보 팝업
        $("#register_submit").on("click", function () {
            if ($("#register_nickName").val() === "") {
                alert("아이디를 입력해주세요.");
                $("#register_nickName").focus();
                return false;
            }
            if ($("#register_pwd").val() === "") {
                alert("비밀번호를 입력해주세요.");
                $("#register_pwd").focus();
                return false;
            }
            if ($("#register_name").val() === "") {
                alert("회원명 입력해주세요.");
                $("#register_name").focus();
                return false;
            }
        });
        $('#emailChk').click(function () { // 회원가입 중복 확인 버튼
            $.ajax({
                url: "${contextPath}/member/duplicatedEmailChk",
                type: "post",
                dataType: "json",
                data: {
                    "email": $("#register_email").val(),
                },
                success: function (data) { // 버튼 클릭 후 return
                    if (data === 1) {
                        alert("이미 사용하고 있는 이메일입니다.");
                    } else if (data === 0) {
                        $("#nickNameChk").attr("value", "Y");
                        alert("사용가능한 이메일입니다.");
                    }
                }
            })
        });
        $('#register_submit').click(function () { // 회원가입 버튼
            $.ajax({
                url: "${contextPath}/member/registerMember",
                type: "post",
                dataType: "json",
                data: {
                    "email": $("#register_email").val(),
                    "pwd": $("#register_pwd").val(),
                    "nickName": $("#register_nickName").val(),
                    "enabled": 1,
                },
                success: function (data) { // 회원 가입 버튼 클릭 후 return
                    if (data === null) {
                        alert("중복되는 아이디 입니다!");
                    } else if (data === true) {
                        alert("환영합니다!");
                        self.location = "${contextPath}/main";
                    }
                }
            });
        });
        $('#login_submit').click(function () { // 로그인 버튼
            $.ajax({
                url: "${contextPath}/member/login",
                type: "post",
                dataType: "json",
                data: {
                    "email": $("#login_email").val(),
                    "pwd": $("#login_pwd").val(),
                },
                success: function (data) { // 로그인 버튼 클릭 후 return
                    if (data === null) {
                        alert("아이디 또는 비밀번호를 확인해주세요.");
                    } else if (data === true) {
                        $("#login_submit").attr("value", "Y");
                        alert("환영합니다.");
                        self.location = "${contextPath}/main";
                    }
                }
            });
        });
        $('#update_submit').click(function () { // 회원정보 수정 버튼
            $.ajax({
                url: "${contextPath}/member/updateMember",
                type: "post",
                dataType: "json",
                data: {
                    "email": $("#update_email").val(),
                    "pwd": $("#update_pwd").val(),
                    "nickName": $("#update_nickName").val(),
                },
                success: function (data) { // 회원정보 수정 후 return
                    if (data === null) {
                        alert("아이디 또는 비밀번호를 확인해주세요.");
                    } else if (data === true) {
                        alert("회원정보 수정이 완료 됐습니다.");
                        self.location = "${contextPath}/main";
                    }
                }
            });
        });
        /*
         * 비밀번호 찾기 버튼
         * 임시 비밀번호 변경 후 이메일 발송
         */
        $('#sendNewPwd').click(function () {
            $.ajax({
                url: "${contextPath}/member/findPwd",
                type: "post",
                dataType: "json",
                data: {
                    "email": $("#findPw_email").val(),
                    "nickName": $("#findPw_nickName").val(),
                },
                success: function (result) {
                    if (result === 1) { // email 일치 X 경우
                        alert("가입되지 않은 이메일 입니다")
                    } else if (result === 2) { // nickName 일치 X 경우
                        alert("가입되지 않은 아이디 입니다");
                    } else if (result === 3) { // email && nickName 일치 X 경우
                        alert("가입되지 않은 이메일과 아이디 입니다");
                    } else { // nickName && email 일치
                        alert("임시 비밀번호를 발송했습니다");
                    }
                }
            });
        });
        $('#page_admin').click(function () {
            location.href = "${contextPath}/admin";
        });
    });
</script>
</body>
</html>