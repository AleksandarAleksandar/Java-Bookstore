<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="center">
	<div>
		<a href="${pageContext.request.contextPath}/"> <img
			src="images/BookstoreLogo.png" />
		</a>
	</div>

	<div class="search-bar">

		<div class="search-group">
			<form action="search" method="get">
				<div class="search-field">
					<input type="text" name="keyword" size="50" />
				</div>
				<div class="search-btn">
					<input type="submit" value="Search" />
				</div>
				<form action="search" method="get">
		</div>
		
		<div class="spacer"> </div>

		<div class="account-links-group">
			<c:if test="${loggedCustomer == null}">
				<a href="login">Sign In</a> |
				<a href="register">Register</a> |			
			</c:if>

			<c:if test="${loggedCustomer != null}">
				<a href="view_profile">Welcome, ${loggedCustomer.fullname}</a> |
				<a href="view_orders">My Orders</a> |
				<a href="logout">Logout</a> |
			</c:if>

			<a href="view_cart">Cart</a>
		</div>


	</div>
	<div class="categories-links-group">
		<c:forEach var="category" items="${listCategory}" varStatus="status">
			<a href="view_category?id=${category.categoryId}"><b><c:out value="${category.name}" /></b></a>
			<c:if test="${not status.last}">|</c:if>
		</c:forEach>
	</div>
</div>