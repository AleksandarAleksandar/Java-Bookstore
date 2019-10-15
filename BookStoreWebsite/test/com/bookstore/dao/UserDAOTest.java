package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {

	
	private static UserDAO userDAO;

	@BeforeClass
	public static void setupClass() throws Exception {
		userDAO = new UserDAO();

	}

	@Test
	public void testCreateUsers() {
		Users user = new Users();
		user.setEmail("aleksic@gmail.com");
		user.setFullName("Ackoa Macko");
		user.setPassword("gorance");

		user = userDAO.create(user);

		assertTrue(user.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users user = new Users();
		user = userDAO.create(user);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("name@gmail.com");
		user.setFullName("Name");
		user.setPassword("changed password");

		user = userDAO.update(user);
		String expected = "changed password";
		String actual = user.getPassword();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);

		assertNotNull(user);
		System.out.println(user.getFullName());
	}

	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);

		assertNull(user);
	}

	@Test
	public void testDeleteUser() {
		Integer userId = 31;
		userDAO.delete(userId);

		Users user = userDAO.get(userId);

		assertNull(user);

	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistingUser() {
		Integer userId = 31;
		userDAO.delete(userId);
	}

	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
	

		assertTrue(listUsers.size() > 0);
	}

	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		
		assertEquals(7, totalUsers);
	}
	@Test
	public void testFindByEmail () {
		String email = "email@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		System.out.println(user.getEmail());
		
		
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "mare@gmail.com";
		String password = "mozda";
		boolean loginResult = userDAO.checkLogin(email, password);
		assertTrue(loginResult);
	}
	@Test
	public void testCheckInFailed() {
		String email = "mare12@gmail.com";
		String password = "mozda12";
		boolean loginResult = userDAO.checkLogin(email, password);
				
		assertFalse(loginResult);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}

}
