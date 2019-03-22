<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử giao dịch</title>
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
	<!-- Display list orders -->
	<div class="main">
		<div class="container">

			<p>Danh sách hóa đơn mua bởi thành viên: ${login_user}</p>

			<div class="table">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Số lượng</th>
							<th>Tổng tiền</th>
							<th>Ngày tạo</th>
							<th>Mã hóa đơn</th>
							<th>Trạng thái</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" scope="page"></c:set>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${count }</td>
								<td>${order.quantity}</td>
								<td><fmt:formatNumber type="number" pattern="###,###"
										value="${order.price}"></fmt:formatNumber> VNĐ</td>
								<td>${order.createdDate }</td>
								<td><a href="invoice-details?id=${order.id}">${order.id}</a></td>
								<td>
									<select>
										<option ${order.status == 1 ? 'selected' : ''} disabled>Đã thanh toán</option>
										<option ${order.status == 0 ? 'selected' : ''} disabled>Chưa thanh toán</option>
									</select>
								</td>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page"></c:set>
						</c:forEach>

					</tbody>
				</table>
				
			</div>

		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="./static/footer.html"></jsp:include>
</body>
</html>