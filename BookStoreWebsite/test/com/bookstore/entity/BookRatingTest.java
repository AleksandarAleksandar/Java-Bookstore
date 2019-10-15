package com.bookstore.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRating() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();

		Review review1 = new Review();
		review1.setRating(2);
		reviews.add(review1);

		Review review2 = new Review();
		review2.setRating(5);
		reviews.add(review2);

		Review review3 = new Review();
		review3.setRating(2);
		reviews.add(review3);

		book.setReviews(reviews);

		float averageRating = book.getAverageRating();

		assertEquals(3.0, averageRating, 0.0);

	}

	@Test
	public void testRatingStringStars1() {
		float averageRating = 0.0f;
		Book book = new Book();
		String actual = book.getRatingString(averageRating);

		String expected = "off,off,off,off,off";

		assertEquals(expected, actual);
	}

	@Test
	public void testRatingStringStars2() {
		float averageRating = 4.5f;
		Book book = new Book();
		String actual = book.getRatingString(averageRating);

		String expected = "on,on,on,on,half";

		assertEquals(expected, actual);
	}

}
