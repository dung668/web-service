<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="client/css/style.css">
	<title>Đăng ký - Web shop online</title>
</head>
<body>
	
	<div class="header">
		<jsp:include page="./static/first-line.html"></jsp:include>
	</div>
	
	<div class="main">
		<div class="container flex flex-center">
			<div class="form-login">
				<h2 class="uppercase">ĐĂNG KÝ</h2>
				<c:if test="${errorMess != null}">
					<div class="errors-message">
						${errorMess}
					</div>
				</c:if>
				<form action="register" method="POST">
					<input class="input-sweet" type="text" name="username" placeholder="Nhập tên tài khoản" required>
					<input class="input-sweet" type="text" name="fname" placeholder="Nhập tên đầy đủ" required>
					<input class="input-sweet" id="password" min="3" max="20" type="password" name="password" placeholder="Nhập mật khẩu" required>
					<input class="input-sweet" id="re-password" type="password" name="re-password" placeholder="Nhập lại mật khẩu" required>
					<input class="input-sweet" type="email" name="email" placeholder="Nhập email" required>
					<div class="flex">
						<a href="./login"><button type="button" class="btn btn-no">Đăng nhập</button></a>
						<button type="submit" class="btn btn-yes">Đăng ký</button>
					</div>
				</form>
				<br/>
				<p><a href="#" style="color: #3a3a3a; text-align: center; text-decoration: underline;">Đăng ký qua Facebook</a></p>
			</div>
		</div>
	</div>
		
	<jsp:include page="./static/footer.html"></jsp:include>
	
	<script type="text/javascript">
		
		var password = document.getElementById("password")
		  , confirm_password = document.getElementById("re-password");
	
		function validatePassword(){
		  if(password.value != confirm_password.value) {
		    confirm_password.setCustomValidity("Password not match!");
		  } else {
		    confirm_password.setCustomValidity('');
		  }
		}
	
		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	
	</script>
	
</body>
</html>