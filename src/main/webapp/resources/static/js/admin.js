$(document).ready(function () {
    /*
     * 계정 정지 버튼
     */
    $('#enabledPause').click(function () {
        $.ajax({
            url: contextPath + "/admin/enabledPause",
            type: "post",
            dataType: "json",
            data: {
                "username": $("#email").val(),
            },
            success: function (data) {
                if (data) {
                    alert("계정 정지 처리 되었습니다.");
                    self.location = contextPath + "/admin/adminPage.do";
                } else {
                    alert("알 수 없는 에러")
                }
            }
        });
    }); // 계정 정지 버튼 끝

    /*
     * 계정 활성화 버튼
     */
    $('#enabledActive').click(function () {
        $.ajax({
            url: contextPath + "/admin/enabledActive",
            type: "post",
            dataType: "json",
            data: {
                "username": $("#email").val(),
            },
            success: function (data) {
                if (data) {
                    alert("계정 활성화 되었습니다.");
                    self.location = contextPath + "/admin/adminPage.do";
                } else {
                    alert("알 수 없는 에러")
                }
            }
        });
    }); // 계정 활성화 버튼 끝
});