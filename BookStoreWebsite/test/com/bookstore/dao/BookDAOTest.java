package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest { 

	private static BookDAO bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}
	@Test
	public void testUpdateBook() throws ParseException, IOException {
	Book exsistingBook = new Book();
	exsistingBook.setBookId(6);

	Category category = new Category("JavaScript");
	category.setCategoryId(10);
	exsistingBook.setCategory(category);

	exsistingBook.setTitle("Effective Java (3nd Edition)");
	exsistingBook.setAuthor("Joshua Bloch");
	exsistingBook.setDescription("desc");
	exsistingBook.setPrice(11.87f);
	exsistingBook.setIsbn("0321356683");

	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	Date publishDate = df.parse("08/28/2008");
	exsistingBook.setPublishDate(publishDate); 
	
	String imagePath = "C:\\Users\\Owner\\Desktop\\eclipseWeb\\workspace\\BookStoreWebsite\\dummydata\\books\\Effective Java.jpg";
	byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
	exsistingBook.setImage(imageBytes);

	Book updatedBook = bookDao.update(exsistingBook);

	assertEquals(updatedBook.getTitle(), "Effective Java (3nd Edition");
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();

		Category category = new Category("PHP");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("desc");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = df.parse("05/28/2008");
		newBook.setPublishDate(publishDate); 
		
		String imagePath = "C:\\Users\\Owner\\Desktop\\eclipseWeb\\workspace\\BookStoreWebsite\\dummydata\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		Book createdBook = bookDao.create(newBook);

		assertTrue(createdBook.getBookId() > 0);

	}
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFailed() {
		Integer bookId = 100;
		bookDao.delete(bookId);
		
		assertTrue(true);
		
	}
	@Test
	public void testDeletBookSuccess() {
		Integer bookId = 6;
		bookDao.delete(bookId);
		
		assertTrue(true);
	}

	@Test
	public void testGetBookFail() {
		Integer bookId=99;
		Book book = bookDao.get(bookId);
		
		assertNull(book);
		
	}
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 7;
		Book book = bookDao.get(bookId);
		
		assertNotNull(book);
	}
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDao.listAll();
		
	
		assertFalse(listBooks.isEmpty());
	}
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		
		Book book = bookDao.findByTitle(title);
		
		assertNull(book);
		
	}
	@Test
	public void testFindByTitle() {
		String title = "Effective Java (2nd Edition)";
		
		Book book = bookDao.findByTitle(title);
		
		System.out.println(book.getTitle());
		assertNotNull(book);
		
	}
	@Test
	public void testCount() {
		int totalBook = (int) bookDao.count();
		assertEquals(2, totalBook);
	}
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDao.listNewBooks();
		
		assertEquals(4, listNewBooks.size());
	}
	@Test
	public void searchBookTitle() {
		
		String keyword = "desc";
		List<Book> result = bookDao.search(keyword);
		
		for(Book book: result) {
			System.out.println(book.getDescription());
		}
		
		assertEquals(3, result.size());
	}
	
	@Test
	public void testListByCategory() {
		int categoryId = 12;
		
		List<Book> listBooks = bookDao.listByCategory(categoryId);
		
		assertTrue(listBooks.size()>0);
		
	}
	@Test
	public void testCountByCateogry() {
		
		int categoryId = 12;
		long numOfBooks = bookDao.countByCategory(categoryId);
		
		assertTrue(numOfBooks==5);
	}
	
}	

