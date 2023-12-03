/**
 * 
 */
        function checkUserId() {
            $.ajax({
                type: "post",
                url: "/login/userIdCheck",
                data: { "userId": $("#memId").val() },
                dataType: "text",
                success: function (result) {
                    if (result.trim() =="사용가능한 아이디입니다.") {
                        alert("사용가능한 아이디입니다.");
                    } else {
                        alert("이미 사용중인 아이디입니다.");
                    }
                },
                error: function () {
                    alert("서버 오류가 발생했습니다.");
                }
            });
        }
        
        function checkUserEmail() {
            $.ajax({
                type: "post",
                url: "/login/userEmailCheck",
                data: { "userEmail": $("#memEmail").val() },
                dataType: "text",
                success: function (result) {
                    if (result.trim() =="사용가능한 이메일입니다.") {
                        alert("사용가능한 이메일입니다.");
                    } else {
                        alert("이미 사용중인 이메일입니다.");
                    }
                },
                error: function () {
                    alert("서버 오류가 발생했습니다.");
                }
            });
        }