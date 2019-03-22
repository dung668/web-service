<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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