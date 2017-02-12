	function change() {
  		 $("#verifyCode").attr("src","validateImg?a=" + new Date().getTime());
  	}
	function clean(){
		$("#messs").html("");
		$("#mess").html("");
		$("#mes").html("");
		$(".tip_name").html("");
		$(".tip_").html("");
		$(".tip_1").html("");
		$(".tip_pass").html("");
		$(".tip_pass2").html("");
		$("img[name='duihao0']").css("display","none"); 
		$("img[name='duihao1']").css("display","none"); 
		$("img[name='duihao2']").css("display","none"); 
		$("img[name='duihao3']").css("display","none"); 
		$("img[name='duihao4']").css("display","none");
		$("img[name='duihao6']").css("display","none");
	}
	$(function() {
		$("#loginbtn").focus(function () {
			var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
			var inputVerifyCode = $("#inputVerifyCode").val();
			var username = $("#username").val();
			var nickname = $("#nickname").val();
			var name = $("input[name='email']").val();
			var pass = $("#password").val();
			var pass2 = $("#password2").val();
			
			
			if(null == username || $.trim(username) == ''){
				alert("请输入用户名");
				$("#username").focus();
			}else if(null == nickname || $.trim(nickname) == ''){
				alert("请输入昵称");
				$("#nickname").focus();
			}else if(null == name || $.trim(name) == ''){
				alert("请输入邮箱");
				$("input[name='email']").focus();
			}else if (!reg.test(name)){
		        alert("请输入正确格式的邮箱！");
		        $("input[name='email']").focus();
		    }else if(null == pass || $.trim(pass) == ''){
				alert("请输入密码");
				$("#password").focus();
			}else if(null == pass2 || $.trim(pass2) == ''){
				alert("请再次输入密码");
				$("#password2").focus();
			}else if(null == inputVerifyCode || $.trim(inputVerifyCode) == ''){
				alert("请输入验证码");
				$("#inputVerifyCode").focus();
			}
		});
	});
	
	$(function() {
  		//验证码
  		$("#inputVerifyCode").mouseleave(function(){
  			var verifyCode = $("#inputVerifyCode").val();
  	  		$.ajax({
  				url:'user_validateVerifyCode.action',
  				type:'POST',
  				data:{'yanzheng':verifyCode},
  				dataType:'json',
  				success:function(data){ 
  			       //获取Action返回的数据用  data.Action中的属性名 获取
   			          if(data==false)
  			          {
   			        	$("img[name='duihao4']").css("display","none");
   			        	$("#tishi").html("验证码输入错误");
  			          }else{
  			            $("#tishi").html("");
  			        	$("img[name='duihao4']").css("display","");
  			          }
  			       }  
  	  		});
  		});
  		
  		//昵称
  		$("#nickname").blur(function(){
  			$("#mess").html("");
  			$(".exit").html("");
  			$(".tip_").html("");
  			$("img[name='duihao1']").css("display","none"); 
  		    $("#tip_").html("");
			var nickname = $("#nickname").val();
	  		$.ajax({
				url:'user_validateNickName.action',
				type:'POST',
				data:{'nickname':nickname},
				dataType:'json',
				success:function(data){ 
					if($.trim(nickname) == "" || nickname.length == 0){
			            $(".tip_").html("<a style='color:red'>昵称不能为空</a>");
			            
			        }  
					else if(data == "用户名不可用"){
						$("#mess").html("昵称不可用");
					}
					
					else if(data=="用户名可用"){
			        	$("img[name='duihao1']").css("display",""); 
			        }
			     }  
	  		});
		});
  		
  		//用户名
  		$("#username").blur(function(){
  			$("#messs").html("");
  			$(".exit").html("");
  			$(".tip_name").html("");
  			$("img[name='duihao6']").css("display","none"); 
  		    $("#tip_name").html("");
			var username = $("#username").val();
	  		$.ajax({
				url:'user_validateName.action',
				type:'POST',
				data:{'username':username},
				dataType:'json',
				success:function(data){ 
					if($.trim(username) == "" || username.length == 0){
			            $(".tip_name").html("<a style='color:red'>用户名不能为空</a>");
			            
			        }  
					else if(data == "用户名不可用"){
						$("#messs").html("用户名不可用");
					}
					
					else if(data=="用户名可用"){
			        	$("img[name='duihao6']").css("display",""); 
			        }
			     }  
	  		});
		});
  		
  		//邮箱
  		$("#email").blur(function(){
  			$("#mes").html("");
  			$(".exit").html("");
  			$(".tip_1").html("");
  			$("img[name='duihao0']").css("display","none"); 
  		    $("#tip_").html("");
			var email = $("#email").val();
			 var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	  		$.ajax({
				url:'user_validateEmail.action',
				type:'POST',
				data:{'email':email},
				dataType:'json',
				success:function(data){ 
					if($.trim(email) == "" || email.length == 0){
			            $(".tip_1").html("<a style='color:red'>邮箱不能为空</a>");
			            
			        }else if (!reg.test(email)){
			 	           $("#mes").html("请输入正确格式的邮箱！");
			 	            return false;
			 	    }
					else if(data == "邮箱名不可用"){
						$("#mes").html("邮箱已存在");
					}
					
					else if(data=="邮箱名可用"){
			        	$("img[name='duihao0']").css("display",""); 
			        }
			     }  
	  		});
		});
  		
  	});
	$(function() {
	    //密码
		$("#password").blur(function(){
			$(".tip_pass").html("");
			$("img[name='duihao2']").css("display","none"); 
			var pass = $("#password").val();
			if ($.trim(pass) == "" || pass.length == 0) {
				$(".tip_pass").html("<a style='color:red'>密码不能为空</a>");
				pass.focus();
			}else if (pass.length<6 || pass.length>10) {
				$(".tip_pass").html("<a style='color:red'>密码的长度必须在6-10个字符</a>");
				pass.select();
			}else{
	        	$("img[name='duihao2']").css("display",""); 
	        }
		});
	    //确定密码
		$("#password2").blur(function(){
			$(".tip_pass2").html("");
			$("img[name='duihao3']").css("display","none"); 
			var pass = $("#password").val();
			var rpass = $("#password2").val();
			if ($.trim(rpass) == "" || rpass.length == 0) {
				$(".tip_pass2").html("<a style='color:red'>密码不能为空</a>");
			}else if (rpass.length<6 || rpass.length>10) {
				$(".tip_pass2").html("<a style='color:red'>密码的长度必须在6-10个字符</a>");
			}else if (rpass != pass) {
				$(".tip_pass2").html("<a style='color:red'>确认密码与密码输入不一致</a>");
			}else{
	        	$("img[name='duihao3']").css("display",""); 
	        }
		});
	    
	});
	$(document).keypress(function(e) {
		if (e.which == 13)
		$("form").submit();
	}) 