<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- List product -->
<c:forEach items="${products}" var="product">
	<div class="item-product">
		<div class="item-wrap">
			<img src="client/images/main-product01.jpg" class="thumb">
			<div class="info">
				<div class="name">
					<a href="product?id=${product.id}">${product.name}</a>
				</div>
				<div class="price-sale flex">
					<div class="price">
						<fmt:formatNumber type="number" pattern="###,###" value="${product.price}"></fmt:formatNumber> VNĐ
					</div>
					<div class="count-sales">${product.category.name}</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>