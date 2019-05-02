<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="client/css/style.css">
	<title>Cập nhật thông tin - Web shop online</title>
</head>
<body>
	
	<div class="header">
		<jsp:include page="./static/first-line.html"></jsp:include>
	</div>
	
	<div class="main">
		<div class="container flex flex-center">
			<div class="form-login">
				<h2 class="uppercase">CẬP NHẬT THÔNG TIN</h2>
				<c:if test="${param.errorMess != null}">
					<div class="errors-message">
						${param.errorMess}
					</div>
				</c:if>
				<form action="update-information" method="POST">
					<input class="input-sweet" type="text" name="username" placeholder="Nhập tên tài khoản (*)" value="${user.username }" ${user != null ? 'disabled' : '' } required>
					<input class="input-sweet" type="password" name="password" placeholder="Nhập mật khẩu (*)" required>
					<input class="input-sweet" type="text" name="address" placeholder="Nhập địa chỉ" value="${user.address }">
					<input class="input-sweet" type="text" min="10" name="phone-number" placeholder="Nhập số điện thoại" value="${user.phone }">
					<div class="flex">
<!-- 						<a href="./register"><button type="button" class="btn btn-no">Đăng ký</button></a> -->
						<button type="submit" class="btn btn-yes">Cập nhật thông tin</button>
					</div>
				</form>
			</div>
		</div>
	</div>
		
	<jsp:include page="./static/footer.html"></jsp:include>
	
</body>
</html>