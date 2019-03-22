<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Trang chủ quản lý</title>
<!-- BOOTSTRAP STYLES-->
<link href="./assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="./assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="./assets/css/custom.css" rel="stylesheet" />
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
						<h2>QUẢN LÝ CHUNG</h2>
					</div>
				</div>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div style="padding: 0 20px;">
						<div class="alert alert-info alert-dismissible">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Xin chào ${login_user}!</strong> Chúc bạn một ngày làm việc hiệu quả.
						</div>

					</div>
				</div>

				<div class="row text-center pad-top">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
						<div class="div-square">
							<a href="users-management"> <i class="fa fa-users fa-5x"></i>
								<span style="font-size: 18px;display: block;">${numberOfUsers} người dùng</span>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
						<div class="div-square">
							<a href="products-management"> <i class="fa fa-sitemap fa-5x"></i>
								<span style="font-size: 18px;display: block;">${numberOfProducts } sản phẩm</span>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
						<div class="div-square">
							<a href="orders-management"> <i class="fa fa-shopping-cart fa-5x"></i>
								<span style="font-size: 18px;display: block;">${numberOfOrders } đơn hàng</span>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
						<div class="div-square">
							<a href="notifications"> <i class="fa fa-bell-o fa-5x"></i>
								<span style="font-size: 18px;display: block;">1 thông báo mới</span>
							</a>
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
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="assets/js/custom.js"></script>
</body>
</html>
