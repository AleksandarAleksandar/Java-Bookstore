package com.bookstore.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {

	private static CustomerDAO customerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("alaaaaaaaaaa@gmail.com");
		customer.setFullname("Fullname Name");
		customer.setCity("LA");
		customer.setCountry("USA");
		customer.setAddress("100 Street Av");
		customer.setPassword("passpass");
		customer.setPhone("123123123");
		customer.setZipcode("1000");

		Customer savedCustomer = customerDao.create(customer);

		assertTrue(savedCustomer.getCustomerId() > 0);

	}

	@Test
	public void testGet() {
		Integer customerId = 17;
		Customer customer = customerDao.get(customerId);

		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(17);
		System.out.println(customer.getFullname());
		String fullName = "Csutoer Csus";
		customer.setFullname(fullName);
		Customer updatedCustomer = customerDao.update(customer);

		assertTrue(updatedCustomer.getFullname().equals(fullName));

	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 17;
		customerDao.delete(customerId);

		Customer customer = customerDao.get(1);

		assertNull(customer);
	}

	@Test
	public void testListAll() {
		List<Customer> listCustomer = customerDao.listAll();
		
		for(Customer customer: listCustomer) {
			System.out.println(customer.getFullname());
		}

		assertFalse(listCustomer.isEmpty());

	}
	@Test
	public void findByEmail() {
		String email = "alaaaaaaaaaa@gmail.com";
		Customer customer = customerDao.findByEmail(email);
		
		assertNotNull(customer);
		
	}
	@Test
	public void testCheckLoginSuccess() {
		String email = "hola@gmail.com";
		String password = "123";
		
		Customer customer = customerDao.checkLogin(email, password);
		assertNotNull(customer);
	}

}
