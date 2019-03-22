<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý sản phẩm</title>
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
						<h2>QUẢN LÝ SẢN PHẨM</h2>
					</div>
				</div>
				<hr />
				<!-- /. ROW  -->
				<div class="row text-center pad-top">

					<div class="col-lg-12 col-md-12">
						<table class="table table-striped table-bordered table-hover">
							<span>Danh sách sản phẩm</span>
							<thead>
								<tr>
									<th>ID</th>
									<th>Tên sản phẩm</th>
									<th>Giá bán</th>
									<th>Tên danh mục</th>
									<th>Mô tả</th>
									<th>Xóa</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="1" scope="page"></c:set>
								<c:forEach items="${products}" var="product">
									<tr>
										<td><a href="products-management?action=EDIT&id=${product.id }">${count }</a></td>
										<td>${product.name }</td>
										<td><fmt:formatNumber type="number" pattern="###,###" value="${product.price}"></fmt:formatNumber> VNĐ</td>
										<td>${product.category.name }</td>
										<td>${fn:substring(product.details, 0, 25)}...</td>
										<td><a href="products-management?action=DELETE&id=${product.id}" onclick="return confirm('Bạn muốn xóa sản phẩm này?')">Xóa</a></td>
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"></c:set>
								</c:forEach>
								
							</tbody>
						</table>

					</div>

					
					<ul class="pagination">
						<li class="${(param.pageId == null || param.pageId == 1) ? 'active' : '' }"><a href="products-management?pageId=1">1</a></li>
						<c:forEach var="i" begin="2" step="1" end="${pages }">
							<li class="${param.pageId == i ? 'active' : '' }"><a href="products-management?pageId=${i }">${i }</a></li>
						</c:forEach>
						
					</ul>
					
				</div>
				
				<hr/>
				
				<h4 class="text-center">Quản lý sản phẩm</h4>
				<form action="products-management" method="POST" id="frm-product">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<input type="hidden" name="product-id" value="${prodct.id }"/>
							<div class="form-group">
	                            <label>Tên sản phẩm</label>
	                            <input class="form-control" value="${prodct.name}" name="product-name">
	                        </div>
	                        <div class="form-group">
	                            <label>Giá bán</label>
	                            <input class="form-control" value="${prodct.price }" name="product-price">
	                        </div>
	                        <div class="form-group">
	                            <label>Tên danh mục</label>
	                            <select class="form-control" name="cateId">
								    <c:forEach items="${categories }" var="category">
								    	<option value="${category.id }" ${category.id == prodct.category.id ? 'selected' : ''}>${category.name }</option>
								    </c:forEach>
								</select>
	                        </div>
						</div>
						<div class="col-lg-6 col-md-6">
							<div class="form-group">
								<label>Mô tả</label>
								<textarea class="form-control" rows="5" name="product-details">${prodct.details }</textarea>
							</div>
							
							<c:if test="${prodct == null }">
								<input type="hidden" name="action" value="ADD"/>
								<button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
							</c:if>
							
							<c:if test="${prodct != null }">
								<input type="hidden" name="action" value="UPDATE"/>
								<button type="submit" class="btn btn-primary">Cập nhật sản phẩm</button>
							</c:if>
						</div>
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
