	function change() {
  		 $("#verifyCode").attr("src","validateImg?a=" + new Date().getTime());
  	}
	function clean(){
		$("#mess").html("");
		$(".tip_").html("");
		$(".tip_pass").html("");
		$(".tip_pass2").html("");
		$("img[name='duihao1']").css("display","none"); 
		$("img[name='duihao2']").css("display","none"); 
		$("img[name='duihao3']").css("display","none"); 
		$("img[name='duihao4']").css("display","none"); 
	}
	$(function() {
		alert("=========");
		$("#loginbtn").focus(function () {
			var inputVerifyCode = $("#inputVerifyCode").val();
			var name = $("input[name='username']").val();
			var pass = $("#password").val();
			var pass2 = $("#password2").val();
			
			if(null == name || $.trim(name) == ''){
				alert("请输入用户名");
			}else if(null == pass || $.trim(pass) == ''){
				alert("请输入密码");
			}else if(null == pass2 || $.trim(pass2) == ''){
				alert("请再次输入密码");
			}else if(null == inputVerifyCode || $.trim(inputVerifyCode) == ''){
				alert("请输入验证码");
			}
		});
	});
	
	$(function() {
  		//验证码
  		$("#inputVerifyCode").blur(function(){
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
   			        	$("#tishi").html("验证码输入错误");
  			          }else{
  			            $("#tishi").html("");
  			        	$("img[name='duihao4']").css("display","");
  			          }
  			       }  
  	  		});
  		});
  		//用户名
  		$("#username").blur(function(){
  			$("#mess").html("");
  			$(".exit").html("");
  			$(".tip_").html("");
  			$("img[name='duihao1']").css("display","none"); 
  		    $("#tip_").html("");
			var username = $("#username").val();
	  		$.ajax({
				url:'user_validateName.action',
				type:'POST',
				data:{'username':username},
				dataType:'json',
				success:function(data){ 
					if($.trim(username) == "" || username.length == 0){
			            $(".tip_").html("<a style='color:red'>用户名不能为空</a>");
			            
			        }  
					else if(data == "用户名不可用"){
						$("#mess").html("用户名不可用");
					}
					
					else if(data=="用户名可用"){
			        	$("img[name='duihao1']").css("display",""); 
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
			/* alert(pass);
			alert(rpass); */
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