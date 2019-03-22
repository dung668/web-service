<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="client/css/style.css">
<script src="client/js/jquery.js"></script>
<script src="client/js/custom/display.js"></script>
<script src="client/js/custom/home.js"></script>
<title>Web shop online</title>
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
			<div class="title-list">Tất cả sản phẩm</div>
<!-- 			<div class="tools-list flex"> -->
<!-- 				<div class="label-tool">Sắp xếp theo</div> -->
<!-- 				<button class="btn btn-selected" id="sort-by-id">Mã sản phẩm</button> -->
<!-- 				<button class="btn btn-no-selected" id="sort-by-name">Tên sản phẩm</button> -->
<!-- 				<button class="btn btn-no-selected" id="sort-by-price">Giá bán</button> -->
<!-- 			</div> -->
			<div class="tools-list flex">
				<div class="flex flex-start">
					<div class="label-tool">Sắp xếp theo</div>
					<button class="btn btn-selected" id="sort-by-id">Mã sản phẩm</button>
					<button class="btn btn-no-selected" id="sort-by-name">Tên sản phẩm</button>
					<button class="btn btn-no-selected" id="sort-by-price">Giá bán</button>
				</div>
				<div class="list-grid">
					<div class="list-view">
						<i class="list-view-icon"></i>
						List View
					</div>
					<div class="grid-view">
						<i class="grid-view-icon"></i>
						Grid View
					</div>
				</div>
			</div>
			
			<div class="list-product flex flex-start" id="list-product">
				<!-- List product -->
				<c:forEach items="${products}" var="product">
					<div class="item-product">
						<div class="item-wrap">
							<img src="client/images/main-product01.jpg" class="thumb">
							<div class="info">
								<div class="name">
									<a href="product?id=${product.id}">${product.name}</a>
								</div>
								<div class="price-sale flex">
									<div class="price">
										<fmt:formatNumber type="number" pattern="###,###" value="${product.price}"></fmt:formatNumber> VNĐ
									</div>
									<div class="count-sales">${product.category.name}</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				
			</div>
			<div id="paginate" class="paginate flex flex-end">
				<a href="shop?page=1" id="page-1" class="page ${(param.page == 1||param.page == null) ? 'current-page' : ''}">1</a>
				<c:forEach begin="2" end="${pages}" var="page">
					<a href="shop?page=${page}" id="page-${page}" class="page ${param.page == page ? 'current-page' : ''}">${page}</a>
				</c:forEach>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="./static/footer.html"></jsp:include>
</body>
</html>