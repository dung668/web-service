<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="client/css/style.css">
<script src="client/js/jquery.js"></script>
<script src="client/js/custom/home.js"></script>
<title>Mua hàng thành công</title>
</head>
<body>
	
	<!-- Header -->
	<div class="header">
		<jsp:include page="./static/first-line.html"></jsp:include>
		<div class="second-line">
			<div class="container flex bg-white">
				<ul class="menu">
					<li class="category">Danh mục</li>
					<!-- Hien thi category -->
					<c:forEach items="${categories}" var="category">
						<li><a href="category?id=${category.id}&page=1">${category.name }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<!-- Show products -->
	<div class="main">
		<div class="container">
			
			<p>Cảm ơn quý khách đã mua hàng tại shop. Quý khách có thể xuất hóa đơn hoặc quay lại <a href="./">Trang chủ</a> để tiếp tục mua hàng.</p>
			
			<a href="report-html?id=${orderId }"><button class="btn btn-yes">Xuất hóa đơn HTML</button></a>
			<a href="report-pdf?id=${orderId }"><button class="btn btn-yes">Xuất hóa đơn PDF</button></a>
			
		</div>
	</div>
	
	<!-- Footer -->
	<jsp:include page="./static/footer.html"></jsp:include>
	
</body>
</html>