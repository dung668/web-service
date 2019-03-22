<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý người dùng</title>
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
						<h2>QUẢN LÝ NGƯỜI DÙNG</h2>
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
									<th>Họ và tên</th>
									<th>Thư điện tử</th>
									<th>Địa chỉ</th>
									<th>Số điện thoại</th>
									<th>Ngày tham gia</th>
									<th>Banned</th>
									<th>Xóa</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="1" scope="page"></c:set>
								<c:forEach items="${users}" var="user">
									<tr>
										<td><a href="users-management?action=EDIT&id=${user.id}">${count }</a></td>
										<td><a href="./orders-management?id=${user.id}">${user.fullName}</a></td>
										<td>${user.email}</td>
										<td>${user.address}</td>
										<td>${user.phone}</td>
										<td>${user.createdDate}</td>
										<td><input type="checkbox" id="cb-ban" value="${user.id}" ${user.banned == true ? 'checked' : ''} ${user.role == 1 ? 'disabled' : '' }/></td>
										<td><a href="users-management?action=DELETE&id=${user.id}" onclick="return confirm('Bạn muốn xóa người dùng này?')">Xóa</a></td>
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"></c:set>
								</c:forEach>
								
							</tbody>
						</table>

					</div>

				</div>
				
				<h4 class="text-center">Quản lý người dùng</h4>
				<form action="users-management" method="POST" id="frm-user">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<input type="hidden" name="user-id" value="${user.id }"/>
							<div class="form-group">
	                            <label>Họ và tên</label>
	                            <input class="form-control" value="${user.fullName}" name="fullName" required>
	                        </div>
	                        <div class="form-group">
	                            <label>Thư điện tử</label>
	                            <input type="email" class="form-control" value="${user.email }" name="email" required>
	                        </div>
	                        <div class="form-group">
	                            <label>Địa chỉ</label>
	                            <input class="form-control" value="${user.address }" name="address" required>
	                        </div>
	                        <div class="form-group">
	                            <label>Số điện thoại</label>
	                            <input class="form-control" value="${user.phone }" name="phone" required>
	                        </div>
						</div>
						<div class="col-lg-6 col-md-6">
							<div class="form-group">
	                            <label>Tên đăng nhập</label>
	                            <input class="form-control" value="${user.username }" name="username" required>
	                        </div>
							<div class="form-group">
	                            <label>Mật khẩu</label>
	                            <input type="password" class="form-control" value="${user.password}" name="password" required>
	                        </div>
	                        <div class="form-group">
	                            <label>Quyền hạn</label>
	                            <select class="form-control" name="user-role">
							    	<option value="1" ${user.role == 1 ? 'selected' : ''}>Quản trị viên</option>
							    	<option value="0" ${user.role == 0 ? 'selected' : ''}>Người dùng</option>
								</select>
	                        </div>
							
							
							<c:if test="${user == null }">
								<input type="hidden" name="action" value="ADD"/>
								<button type="submit" class="btn btn-primary">Thêm người dùng</button>
							</c:if>
							
							<c:if test="${user != null }">
								<input type="hidden" name="action" value="UPDATE"/>
								<button type="submit" class="btn btn-primary">Cập nhật thông tin</button>
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
