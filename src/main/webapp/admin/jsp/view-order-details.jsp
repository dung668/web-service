<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý chi tiết hóa đơn mua hàng</title>
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
						<h2>QUẢN LÝ CHI TIẾT</h2>
					</div>
				</div>
				<hr />
				<!-- /. ROW  -->
				<div class="row text-center pad-top">

					<div class="row">
						<div class="col-lg-12 col-md-12" style="padding: 0 60px;">
							<h5>Chi tiết hóa đơn</h5>
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
						
					</div>

				</div>
				<div class="row text-center">
					<a href="report-html?id=${param.id}"><button type="button" class="btn btn-primary">Xuất hóa đơn HTML</button></a>
					<a href="report-pdf?id=${param.id}"><button type="button" class="btn btn-info">Xuất hóa đơn PDF</button></a>
				</div>
				<!-- /. PAGE INNER  -->
			</div>
			<!-- /. PAGE WRAPPER  -->
		</div>
		<!-- FOOTER -->
		<jsp:include page="./inc/footer.html"></jsp:include>
		<!-- /. FOOTER -->

	</div>
</body>
</html>
