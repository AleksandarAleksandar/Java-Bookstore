package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(12);

		Customer customer = new Customer();
		customer.setCustomerId(19);

		review.setBook(book);
		review.setCustomer(customer);

		review.setHeadline("Excellent book");
		review.setRating(4);
		review.setComment("I loved the book");

		Review savedReview = reviewDao.create(review);

		assertTrue(savedReview.getReviewId() > 0);

	}

	@Test
	public void testGet() {
		Integer reviewId = 16;
		Review review = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}
	@Test
	public void testUpdateReview() {
		Integer reviewId = 16;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Awesome book");
		Review updatedReview = reviewDao.update(review);
		
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
	}

	@Test
	public void testDeleteObject() {
		int reviewId = 16;
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
		assertTrue(listReview.size()>0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		
		assertTrue(totalReviews>0);
	}
	@Test 
	public void findByCustomerAndBookNotFound() {
		
		Integer customerId = 19;
		Integer bookId = 11;
		
		Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(result);
	
		
	}
}
