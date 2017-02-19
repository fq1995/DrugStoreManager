function change() {
	$("#verifyCode").attr("src", "validateImg?a=" + new Date().getTime());
}
function verify() {
	var verifyCode = $("#inputVerifyCode").val();
	$.ajax({
		url : 'user_validateVerifyCode.action',
		type : 'POST',
		data : {
			'yanzheng' : verifyCode
		},
		dataType : 'json',
		success : function(data) {
			if (data == false) {
				alert("请输入正确的验证码");
				$("#inputVerifyCode").focus();
				return false;
			}else {
				return true;
			}
		}
	});
};
$(function() {
	$("#loginbtn").focus(function () {
		var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		var inputVerifyCode = $("#inputVerifyCode").val();
		var name = $("input[name='email']").val();
		var pass = $("#password").val();
		if(null == name || $.trim(name) == ''){
			alert("请输入邮箱账号");
			$("input[name='email']").focus();
		}else if (!reg.test(name)){
	        alert("请输入正确格式的邮箱！");
	        $("input[name='email']").focus();
	    }else if(null == pass || $.trim(pass) == ''){
			alert("请输入密码");
			$("#password").focus();
		}else if(null == inputVerifyCode || $.trim(inputVerifyCode) == ''){
			alert("请输入验证码");
			$("#inputVerifyCode").focus();
		}
		/*else if(verify()){
			
		}
		*/
	});
});


$(function() {
	//验证码
	$("#inputVerifyCode").mouseleave(function() {
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
					$("img[name='duihao4']").css("display", "none");
					$("#tishi").html("验证码输入错误");
				}
				if (data == false) {
					$("img[name='duihao4']").css("display", "none");
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
	
	// 用户帐号非空
	$("#email").blur(function() {
		var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		$(".tip_").html("");
		var name = $("input[name='email']").val();
		$("img[name='duihao1']").css("display", "none");
		$("#tip_").html("");
		if ($.trim(name) == "" || name.length == 0) {
			$(".tip_").html("<a style='color:red'>用户帐号不能为空</a>");
			$("input[name='email']").focus();
		}else if (!reg.test(name)){
	        $(".tip_").html("<a style='color:red'>请输入正确格式的邮箱！</a>");
	        $("input[name='email']").focus();
	        return false;
	    }else {
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
$(document).keypress(function(e) {
	if (e.which == 13)
	$("form").submit();
}) 
