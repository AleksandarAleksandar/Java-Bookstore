<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${book.title}-Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">

		<div class="single-view">
			<div class="book-item">
				<div class="image">
					<a href="view_book?id=${book.bookId}"> <img class=""
						src="data:image/jpg;base64,${book.base64Image}" />
					</a>
				</div>
				<div class="search-info">
					<div>
						<h1>
							<a href="view_book?id=${book.bookId}"> <b>${book.title}</b></a>
						</h1>
					</div>
					<div><jsp:directive.include file="book_rating.jsp" /></div>
					<div class="author">by ${book.author}</div>
					<p class="description">${fn:substring(book.description, 0, 100)}...</p>

				</div>
				<div class="price-group">
					<p class="price">$${book.price}</p>
					<div class="add-to-cart">
						<a href="add_to_cart?book_id=${book.bookId}">Add To Cart</a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>


		<table class="book">
			<tr>
				<td><h2>
						<a id="reviews">Customer Reviews</a>
					</h2></td>
				<td colspan="2" align="center">
					<button id="buttonWriteReview">Write a Customer Review</button>
				</td>
			</tr>

			<tr>
				<td colspan="3" align="left">
					<table class="normal">
						<c:forEach items="${book.reviews}" var="review">
							<tr>
								<td><c:forTokens items="${review.stars}" delims=","
										var="star">
										<c:if test="${star eq 'on'}">
											<img src="images/rating_on.png" />
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src="images/rating_off.png" />
										</c:if>
									</c:forTokens> - <b>${review.headline}</b></td>
							</tr>
							<tr>
								<td>by ${review.customer.fullname} on ${review.reviewTime}
								</td>
							</tr>
							<tr>
								<td><i>${review.comment}</i></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>

		</table>


	</div>

	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#buttonWriteReview").click(function() {
				window.location = 'write_review?book_id=' + $
				{
					book.bookId
				}
				;
			});

			$("#buttonAddToCart").click(function() {
				window.location = 'add_to_cart?book_id=' + $
				{
					book.bookId
				}
				;
			});
		});
	</script>
</body>
</html>