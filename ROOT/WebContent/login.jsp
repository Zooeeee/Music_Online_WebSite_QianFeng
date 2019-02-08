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
	<!-- Jquery -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- //Jquery -->

	<!-- Video js -->
	<script src="js/jquery.vide.min.js"></script>
	<!-- //Video js -->
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
	<link rel="stylesheet" href="./css/style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="./css/fontawesome-all.css">
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
			<span>W</span>elcome
		</h1>
		<!-- //title -->
		<!-- content -->
		<div class="sub-main-w3">
			<form action="UserLoginServlet" id="myform" method="post">
			${tips}
				<div class="form-style-agile">	
					<label>
						<i class="fas fa-user"></i>用户名</label>
					<input placeholder="Username" name="name" type="text" required="">
				</div>
				<div class="form-style-agile">
					<label>
						<i class="fas fa-unlock-alt"></i>密码</label>
					<input placeholder="Password" name="pwd" type="password" required="">
				</div>	
				<!-- switch -->
				<div class="checkout-w3l">
					<div class="demo5">
						<div class="switch demo3">
							<input type="checkbox">
							<label>
								<i></i>
							</label>
						</div>
					</div>
					<a href="#">Remember Me</a>
				</div>
				<!-- //switch -->
				<input type="submit" value="登录">	
				<input id = "zhanghang"type="button" value="注册" onclick="setup()">	
				<script type="text/javascript">
				function setup() {
					window.location.href="setup.jsp";
				}
				</script>
			</form>
		</div>
		<!-- //content -->
	</div>
	
	
</body>

</html>