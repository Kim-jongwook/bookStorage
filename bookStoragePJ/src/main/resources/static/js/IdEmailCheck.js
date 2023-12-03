/**
 * 
 */
		$(function(){
			function checkEnableSubmit(){
				if($("#checkBtn1").text() == "사용 취소" && $("#checkBtn2").text() == "사용 취소"){
					$("#submit").prop("disabled", false);
					$("#submit").css("background-color", "#5692bc");
					$("#submit").css("cursor", "pointer");
				}else{
					$("#submit").prop("disabled", true);
					$("#submit").css("background-color", "#eee");
					$("#submit").css("cursor", "not-allowed");
				}
			}
			setInterval(checkEnableSubmit, 100);
		});

        function checkUserId() {
            $.ajax({
                type: "post",
                url: "/login/userIdCheck",
                data: { "userId": $("#memId").val() },
                dataType: "text",
                success: function (result) {
                    if (result.trim() =="사용가능한 아이디입니다.") {
						if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")){
							$("#memId").prop("readonly", true);
							$("#memId").css("background-color", "#eee");
							$("#checkBtn1").text("사용 취소");
							$("#checkBtn1").attr("onclick", "cancelUserId()");
						}else{}
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
						if(confirm("사용 가능한 이메일입니다. 사용하시겠습니까?")){
							$("#memEmail").prop("readonly", true);
							$("#memEmail").css("background-color", "#eee");
							$("#checkBtn2").text("사용 취소");
							$("#checkBtn2").attr("onclick", "cancelUserEmail()");
						}else{}
                    } else {
                        alert("이미 사용중인 이메일입니다.");
                    }
                },
                error: function () {
                    alert("서버 오류가 발생했습니다.");
                }
            });
        }
   		function cancelUserId(){
			   $("#memId").prop("readonly", false);
			   $("#memId").css("background-color", "#fff");
			   $("#checkBtn1").text("중복 확인");
			   $("#checkBtn1").attr("onclick", "checkUserId()");
		}
        function cancelUserEmail(){
			   $("#memEmail").prop("readonly", false);
			   $("#memEmail").css("background-color", "#fff");
			   $("#checkBtn2").text("중복 확인");
			   $("#checkBtn2").attr("onclick", "checkUserEmail()");
		}