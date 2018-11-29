<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能教室后台管理系统</title>

<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/adminjs/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/adminjs/jquery.form.js"></script>
<title>Insert title here</title>
<script>
	$(function() {
		//异步提交表单
        $("#submit").click(function(){
            $.ajax({
                url: "${pageContext.request.contextPath}/user/login.do",
                type: "post",
                data:{"name":$("#name").val(),"pass":$("#pass").val()},
                dataType: "json",  //接收数据的类型
                async: true,
                success: function(json){
                    if (json.status == true) { //如果登录成功，那么就跳转到网站首页
                        location = "index.jsp";
                    } else { 	//如果登录失败，那么就提示用户
                        $("input[name='pass']").val("");
                        alert(json.message);
                    }
                },
                error: function(){
                    alert("请求失败");
                }
            });
		});


	});
</script>
</head>
<body>
	<div class='signup_container'>
		<div class="w-load">
			<div class="spin"></div>
		</div>
		<h1 class='signup_title'>智能教室后台管理系统</h1>
		<div id="userInfo">
			<span
				style="float: left; margin-left: 40px; height: 200px; border: 0px solid red"><img
				src='images/logo.png' id='admin' /></span> <span
				style="float: left; margin-left: 40px; height: 200px; border: 0px solid red">
				<div id="signup_forms" class="signup_forms clearfix">
					<form class="signup_form_form" id="loginForm" method="post">
						<div class="form_row first_row">
							<label for="signup_email">请输入用户名</label> <input type="text"
								id="name" name="name" placeholder="请输入用户名" id="signup_name">
						</div>
						<div class="form_row">
							<label for="signup_password">请输入密码</label> <input type="password"
								id="pass" name="pass" placeholder="请输入密码" id="signup_password">
						</div>
					</form>
				</div>
				<div id="foo"></div> <br />
			</span>
		</div>
		<div class="login-btn-set">
			<div class='rem'>记住我</div>
			<a  id = "submit" class='login-btn'></a>
		</div>
	</div>
</body>
</html>