<div class="book-item">
	<div class="image">
		<a href="view_book?id=${book.bookId}"> <img class="book-small"
			src="data:image/jpg;base64,${book.base64Image}" />
		</a>
	</div>
	<div class="search-info">
		<div>
			<h3>
				<a href="view_book?id=${book.bookId}"> <b>${book.title}</b></a>
			</h3>
		</div>
		<div><jsp:directive.include file="book_rating.jsp" /></div>
		<div class="author">by ${book.author}</div>
	</div>
	<div class="price-group">
		<p class="price">$${book.price}</p>
		<div class="add-to-cart">
			<a href="add_to_cart?book_id=${book.bookId}">Add To Cart</a>
		</div>
	</div>
	<div class="clearfix"></div>
</div>