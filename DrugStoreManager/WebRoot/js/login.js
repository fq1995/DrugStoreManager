function change() {
	$("#verifyCode").attr("src", "validateImg?a=" + new Date().getTime());
}
$(function() {
	$("#loginbtn").focus(function () {
		var inputVerifyCode = $("#inputVerifyCode").val();
		var name = $("input[name='email']").val();
		var pass = $("#password").val();
		if(null == name || $.trim(name) == ''){
			alert("请输入邮箱账号");
		}else if(null == pass || $.trim(pass) == ''){
			alert("请输入密码");
		}else if(null == inputVerifyCode || $.trim(inputVerifyCode) == ''){
			alert("请输入验证码");
		}
	});
});


$(function() {
	
	$("#inputVerifyCode").blur(function() {
		var verifyCode = $("#inputVerifyCode").val();
		$.ajax({
			url : 'user_validateVerifyCode.action',
			type : 'POST',
			data : {
				'yanzheng' : verifyCode
			},
			dataType : 'json',
			success : function(data) {
				if (verifyCode.trim.length == 0) {
					$("#tishi").html("验证码输入错误");
				}
				if (data == false) {
					$("#tishi").html("验证码输入错误");
				} else {
					$("#loginbtn").css("disabled",false);
					$("#tishi").html("");
					$("img[name='duihao4']").css("display", "");
				}
			}
		});
	});
});
function clean() {
	$("#tish").html("");
	$("#mess").html("");
	$("#tishi").html("");
	$(".tip_").html("");
	$(".tip_pass").html("");
	$("img[name='duihao1']").css("display", "none");
	$("img[name='duihao2']").css("display", "none");
	$("img[name='duihao4']").css("display", "none");
}
$(function() {
	// 用户名非空
	$("#email").blur(function() {
		$(".tip_").html("");
		var name = $("input[name='email']").val();
		$("img[name='duihao1']").css("display", "none");
		$("#tip_").html("");
		if ($.trim(name) == "" || name.length == 0) {
			$(".tip_").html("<a style='color:red'>用户名不能为空</a>");
			return;
		} else {
			$("img[name='duihao1']").css("display", "");
		}
	});

	// 密码
	$("#password").blur(function() {
		$(".tip_pass").html("");
		$("img[name='duihao2']").css("display", "none");
		var pass = $("#password").val();
		if ($.trim(pass) == "" || pass.length == 0) {
			$(".tip_pass").html("<a style='color:red'>密码不能为空</a>");
		} else if (pass.length < 6 || pass.length > 10) {
			$(".tip_pass").html("<a style='color:red'>密码的长度必须在6-10个字符</a>");
		} else {
			$("img[name='duihao2']").css("display", "");
		}
	});
});
$(function() {
	$("#loginbtn").onMouseUp(function(){
		
		var verifyCode = $("#inputVerifyCode").val();
		if (verifyCode.trim.length != 0) {
			
			$("#loginbtn").css("disabled",false);
		}
		
	})
	
	$("#email").focus(function() {
		$("#ti").html("");
	});
	$("#password").focus(function() {
		$("#ti").html("");

	});
	$("#inputVerifyCode").focus(function() {
		$("#ti").html("");

	});

});