<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý danh mục sản phẩm</title>
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
						<h2>QUẢN LÝ DANH MỤC SẢN PHẨM</h2>
					</div>
				</div>
				<hr />
				<!-- /. ROW  -->
				<div class="row text-center pad-top">

					<div class="col-lg-12 col-md-12">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Tên danh mục</th>
									<th>Sửa</th>
									<th>Xóa</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="1" scope="page"></c:set>
								<c:forEach items="${categories}" var="category">
									<tr>
										<td>${count }</td>
										<td>${category.name }</td>
										<td><a href="categories-management?action=EDIT&id=${category.id }">Sửa</a></td>
										<td><a href="categories-management?action=DELETE&id=${category.id }">Xóa</a></td>
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"></c:set>
								</c:forEach>
								
							</tbody>
						</table>

					</div>
						
				</div>
				
				<h4 class="text-center">Quản lý danh mục</h4>
				<form action="categories-management" method="POST" id="frm-category">
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
							<input type="hidden" name="category-id" value="${category.id }"/>
							<div class="form-group">
	                            <label>Tên danh mục</label>
	                            <input class="form-control" value="${category.name}" name="category-name" required>
	                        </div>
						</div><br/>
						<div class="col-lg-3 col-md-3">
							<c:if test="${category == null }">
								<input type="hidden" name="action" value="ADD"/>
								<button type="submit" class="btn btn-primary">Thêm danh mục</button>
							</c:if>
							
							<c:if test="${category != null }">
								<input type="hidden" name="action" value="UPDATE"/>
								<button type="submit" class="btn btn-primary">Cập nhật thông tin</button>
							</c:if>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
					</div>
				</form>
				
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
