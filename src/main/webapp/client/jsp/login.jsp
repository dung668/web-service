<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="client/css/style.css">
	<title>Đăng nhập - Web shop online</title>
</head>
<body>
	
	<div class="header">
		<jsp:include page="./static/first-line.html"></jsp:include>
	</div>
	
	<div class="main">
		<div class="container flex flex-center">
			<div class="form-login">
				<h2 class="uppercase">ĐĂNG NHẬP</h2>
				<c:if test="${errorMess != null}">
					<div class="errors-message">
						${errorMess}
					</div>
				</c:if>
				<form action="login" method="POST">
					<input class="input-sweet" type="text" name="username" placeholder="Nhập tên tài khoản">
					<input class="input-sweet" type="password" name="password" placeholder="Nhập mật khẩu">
					<div class="flex">
						<a href="./register"><button type="button" class="btn btn-no">Đăng ký</button></a>
						<button type="submit" class="btn btn-yes">Đăng nhập</button>
					</div>
				</form>
				<br/>
				<p><a href="https://www.facebook.com/dialog/oauth?client_id=357862431521823&redirect_uri=https://localhost:8443/eshop/fb-login&scope=email" style="color: #3a3a3a; text-align: center; text-decoration: underline;">Đăng nhập bằng Facebook</a></p>
			</div>
		</div>
	</div>
		
	<jsp:include page="./static/footer.html"></jsp:include>
	
</body>
</html>