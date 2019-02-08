<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zxx">

<head>
	<title>Video Login Form Responsive Widget Template :: w3layouts</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content="Video Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements"
	/>
	<style type="text/css">
		#myform label.error{
			color: wheat;
			font-size: 12px;
		}
	</style>
	<!-- Jquery -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- //Jquery -->
	<!-- Video js -->
	<script src="js/jquery.vide.min.js"></script>
	<!-- //Video js -->
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript">
		//在当前网页加载时候就声明对表单进行校验
		$(document).ready(function(){
			$("#myform").validate({
				rules:{
					userName:{required:true,minlength:6,maxlength:20},
					userPwd:{required:true,minlength:8,maxlength:16},
					rePwd:{required:true,equalTo:"#userPwd"}
				},
				messages:{
					userName:{required:"请输入账号！",minlength:"账号长度不能小于6个字符",maxlength:"账号长度不能大于20个字符"},
					userPwd:{required:"请输入密码！",minlength:"密码长度不能小于8个字符",maxlength:"密码长度不能大于16个字符"},
					rePwd:{required:"请再次输入密码！",equalTo:"两次密码输入不一致"}
				}
			});
		});
	</script>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);
		
		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="css/fontawesome-all.css">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->
	<!-- web-fonts -->
	<link href="//fonts.googleapis.com/css?family=Marck+Script&amp;subset=cyrillic,latin-ext" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=cyrillic,latin-ext"
	    rel="stylesheet">
	<!-- //web-fonts -->
</head>

<body>
	<div class="video-w3l" data-vide-bg="video/1">
		<!-- title -->
		<h1>
			<span>S</span>etup
		</h1>
		<!-- //title -->
		<!-- content -->
		<div class="sub-main-w3">
			<form action="UserRegistServlet" id="myform" method="post" enctype="multipart/form-data">
				${tips }
				<div class="form-style-agile">
					<label>
						<i ></i>用户名</label>
					<input placeholder="Username" name="userName" type="text">
				</div>
				<div class="form-style-agile">
					<label>
						<i ></i>密码</label>
					<input placeholder="Password" name="userPwd" id="userPwd" type="password">
				</div>
				<div class="form-style-agile">
					<label>
						<i ></i>确认密码</label>
					<input placeholder="Password" name="rePwd" type="password">
				</div>	
				<div class="form-style-agile">
					<label>
						<i ></i>昵称</label>
					<input placeholder="NickName" name="userNick" type="text" required="">
				</div>	
				<div class="form-style-agile">
					<label>
						<i ></i>性别</label>
						<input type="radio" name= "userSex" value="男">男
						<input type="radio" name= "userSex" value="女">女
				</div>	
				<div class="form-style-agile">
					<label>
						<i ></i>图片</label>
					<input  name="userPic" type="file">
				</div>	
				<div class="form-style-agile">
					<label>
						<i ></i>个性签名:</label>
					<textarea name="userDesc"></textarea>
					<!-- <input placeholder="Desc" name="desc" type="text" required=""> -->
				</div>	
				<input type="submit" value="注册">	
			</form>
		</div>
		<!-- //content -->
	</div>
	
	
</body>

</html>