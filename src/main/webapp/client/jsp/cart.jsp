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
<script src="client/js/custom/cart.js"></script>
<title>Giỏ hàng của bạn</title>
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
				<a href="./">Trang chủ</a> > Giỏ hàng
			</div>
			<c:if test="${order == null}">
				<p>
					Giỏ hàng của bạn đang trống, mua hàng rồi quay lại nhé! <a
						href="./">Về trang chủ</a>
				</p>
			</c:if>
			<c:if test="${order != null}">
				<div class="box-wrap flex flex-center"
					style="flex-direction: column; align-items: center;">
					
					<div class="table">
						<table class="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Hình ảnh</th>
									<th>Tên sản phẩm</th>
									<th>SL</th>
									<th>Action</th>
									<th>Giá</th>
									<th>Thành tiền</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="1" scope="page"></c:set>
								<c:forEach items="${order.items}" var="item">
									<tr>
									
										<td>${count}</td>
										<td><img src="client/images/main-product01.jpg" style="width: 50px;"></td>
										<td><a href="product?id=${item.product.id}">${item.product.name }</a></td>
										<td class="text-center"><input type="number" min="1"
											class="input-sweet input-number" value="${item.quantity}"></td>
										<td><a href="delete-item?product=${item.product.id}">Xóa</a></td>
										<td><fmt:formatNumber type="number" pattern="###,###" value="${item.product.price}"></fmt:formatNumber> VNĐ</td>
										<td><fmt:formatNumber type="number" pattern="###,###" value="${item.product.price * item.quantity}"></fmt:formatNumber> VNĐ</td>
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
					
					<div style="margin-top: 30px">
						<div class="flex">
							<select name="paymethod" class="input-sweet" required="required"
								style="margin-right: 10px; margin-bottom: 0px">
								<option value="cod">COD (thanh toán khi nhận hàng)</option>
								<option value="atm">Thanh toán qua ATM</option>
							</select>
							<!-- paypal -->
							<form action="${initParam.posturl}" method="post">
						
								<input type="hidden" name="upload" value="1">
								<input type="hidden" name="return" value="${initParam.returnurl}">
								<input type="hidden" name="cmd" value="_cart">
								<input type="hidden" name="business" value="${initParam.business}">
								
								<c:set var="count" value="1" scope="page"></c:set>
								<c:forEach items="${order.items}" var="item">
								
									<input type="hidden" name="item_name_${count}" value="${item.product.name }">
									<input type="hidden" name="amount_${count}" value="${item.product.price}">
									<input type="hidden" name="quantity_${count}" value="${item.quantity}">
									<c:set var="count" value="${count + 1}" scope="page"></c:set>
									
								</c:forEach>
								
								<input class="btn btn-yes" type="submit" value="Tiến hành thanh toán">
								
							</form>
<!-- 							<a href="order"><button class="btn btn-yes">Tiến hành thanh toán</button></a> -->
						</div>
					</div>
					<hr>
					
				</div>
			</c:if>

		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="./static/footer.html"></jsp:include>

</body>
</html>