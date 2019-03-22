<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Thông báo bình luận</title>
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
						<h2>THÔNG BÁO BÌNH LUẬN</h2>
					</div>
				</div>
				<hr />
				<!-- /. ROW  -->
				<div class="row text-center pad-top">

					<div class="row">
						<div class="col-lg-12 col-md-12" style="padding: 0 60px;">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên Người Dùng</th>
										<th>Bình Luận</th>
										<th>Bài Viết</th>
										<th>Thời Gian</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="1" scope="page"></c:set>
									<c:forEach items="${notifications}" var="noti">
										<tr>
											<td>${count }</td>
											<td>${noti.username }</td>
											<td>${noti.message }</td>
											<td><a href="http://localhost:8080/eshop/product?id=${noti.id_post}#dnd-comment" target="_blank">Đến bài viết</a></td>
											<td>${noti.created_at }</td>
										</tr>
										<c:set var="count" value="${count+1 }" scope="page"></c:set>
									</c:forEach>
									
								</tbody>
							</table>

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
</body>
</html>
