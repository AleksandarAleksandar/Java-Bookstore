package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {

	private static CategoryDAO categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		categoryDao = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("JavaScript");
		Category category = categoryDao.create(newCat);

		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Java Core");
		cat.setCategoryId(1);

		Category category = categoryDao.update(cat);

		assertEquals(cat.getName(), category.getName());

	}

	@Test
	public void testGet() {
		Integer catId = 8;
		Category cat = categoryDao.get(catId);

		System.out.println(cat.getName());
		assertNotNull(cat);
	}

	@Test
	public void testDeleteObject() {
		Integer catId = 8;
		categoryDao.delete(catId);

		Category cat = categoryDao.get(catId);

		assertNull(cat);

	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();
		listCategory.forEach(c -> System.out.println(c.getName() + ", " ));
		
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDao.count();
		
		assertEquals(totalCategories, 3);
	}
	@Test
	public void testFindByName() {
		
		String name = "JavaScript";
		Category category =  categoryDao.findByName(name);
		assertNotNull(category);
	}
	@Test
	public void testFindByNameNotFound() {
		
		String name = "JavaScripttt";
		Category category =  categoryDao.findByName(name);
		assertNull(category);
	}
}
