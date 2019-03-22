<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý hóa đơn mua hàng</title>
<!-- BOOTSTRAP STYLES-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->	
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>

<body>

	<div id="wrapper">
		<!-- NAV BAR -->
		<jsp:include page="./inc/navbar.html"></jsp:include>
		<!-- /. NAV BAR -->
		<!-- /. NAV TOP  -->
		<jsp:include page="./inc/menu.jsp"></jsp:include>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-lg-12">
						<h2>QUẢN LÝ HÓA ĐƠN</h2>
					</div>
				</div>
				<hr />
				<!-- /. ROW  -->
				<div class="row text-center pad-top">

					<div class="row">
						<div class="col-lg-12 col-md-12" style="padding: 0 60px;">
							
							<c:if test="${buyer != null }">
								<h5>Hóa đơn được mua bởi thành viên: ${buyer}</h5>
							</c:if>
							<div id="table-order">
								<table class="table table-striped table-bordered table-hover">
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
												<td><fmt:formatNumber type="number" pattern="###,###" value="${order.price}"></fmt:formatNumber> VNĐ</td>
												<td>${order.createdDate}</td>
												<td><a class="orderid" href="view-order-details?id=${order.id}">${order.id}</a></td>
												<td class="${order.id }">
													<select class="change-order-status">
														<option value="1" ${order.status == 1 ? 'selected' : ''}>Đã thanh toán</option>
														<option value="0" ${order.status == 0 ? 'selected' : ''}>Chưa thanh toán</option>
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

				</div>
				<!-- /. PAGE INNER  -->
			</div>
			<!-- /. PAGE WRAPPER  -->
		</div>
		<!-- FOOTER -->
		<jsp:include page="./inc/footer.html"></jsp:include>
		<!-- /. FOOTER -->

	</div>
	<script src="assets/js/jquery-1.10.2.js"></script>
	<script src="assets/js/custom.js"></script>
</body>
</html>
