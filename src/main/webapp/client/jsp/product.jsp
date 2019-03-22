<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem sản phẩm - ${product.name}</title>
<meta property="fb:app_id" content="210865283197643"/>
<link rel="stylesheet" type="text/css" href="client/css/style.css">
<script src="client/js/jquery.js"></script>
<script src="client/js/custom/product.js"></script>

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
						<li><a href="category?id=${category.id}">${category.name }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="container">
			<div class="breadcumb">
				<a href="./">Trang chủ</a> > <a href="category?id=${product.category.id}">${product.category.name}</a> > ${product.name}
			</div>
			<div class="box-wrap flex flex-start">
				<div class="image-info">
					<img src="client/images/main-product01.jpg" class="image-info-img">
				</div>
				<div class="detail-info flex flex-column">
					<div class="name-info">${product.name}</div>
					<div class="price-info"><fmt:formatNumber type="number" pattern="###,###" value="${product.price}"></fmt:formatNumber> VNĐ</div>
					<div class="number-info">
						Số lượng <input type="number" value="1" min="1" class="input-sweet input-number">
					</div>
					<div class="empty-box">
<!-- 						<a href="add-to-cart" class="add-to-cart">Thêm vào giỏ hàng</a> -->
						<form action="add-to-cart" method="GET">
							<input type="hidden" name="id" value="${product.id}">
							<input type="hidden" name="quantity" value="1" id="product-quantity">
							<input type="submit" value="Thêm vào giỏ hàng" class="check-out"/>
							<a href="wishlist?id=${product.id}" class="add-to-cart">Thêm vào yêu thích</a>
						</form>
					</div>
				</div>
			</div>

			<div class="box-wrap flex flex-start">
				<div class="title-sweet">Chi tiết sản phẩm</div>
				<p class="detail-info-product">
					${product.details}
				</p>
			</div>

			<div class="box-wrap flex flex-start">
				<div class="title-sweet">bình luận</div>
				
				<div id="dnd-comment" class="fb-comments" data-href="http://blogvatly97.blogspot.com/id/${product.id }" data-width="1160" data-numposts="3"></div>
				
			</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="./static/footer.html"></jsp:include>
	
	
</body>
</html>