<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết hóa đơn</title>
<link rel="stylesheet" type="text/css" href="client/css/style.css">
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

	<div class="main">
		<div class="container">
			<div class="breadcumb">
				<a href="./">Trang chủ</a> > Chi tiết giỏ hàng
			</div>
			<div style="float: right;">
				<a href="report-html?id=${param.id }"><button class="btn btn-yes inline">Xuất hóa đơn HTML</button></a>
				<a href="report-pdf?id=${param.id }"><button class="btn btn-yes inline">Xuất hóa đơn PDF</button></a>
			</div>
			<div style="clear:both;"></div>
			<div class="box-wrap flex flex-center"
				style="flex-direction: column; align-items: center;">
				<div class="table">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Tên sản phẩm</th>
								<th>Giá bán</th>
								<th>Mã hóa đơn</th>
								<th>Số lượng</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1" scope="page"></c:set>
							<c:forEach items="${items}" var="item">
								<tr>
									<td>${count }</td>
									<td>${item.product.name}</td>
									<td><fmt:formatNumber type="number" pattern="###,###"
											value="${item.price}"></fmt:formatNumber> VNĐ</td>
									<td>${item.id}</td>
									<td>${item.quantity}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"></c:set>
							</c:forEach>
							
							<tr>
								<td colspan="3"><strong>Tổng cộng : ${quantity }</strong></td>
								<td colspan="2" style="color: red;"><fmt:formatNumber type="number" pattern="###,###" value="${price}"></fmt:formatNumber> VNĐ</td>
							</tr>
							
						</tbody>
					</table>
					
					
				</div>
				
				
				
				
				<hr>
			</div>


		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="./static/footer.html"></jsp:include>

</body>
</html>