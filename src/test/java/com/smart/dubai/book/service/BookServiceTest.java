package com.smart.dubai.book.service;

import com.smart.dubai.book.service.entity.Book;
import com.smart.dubai.book.service.model.BookBean;
import com.smart.dubai.book.service.model.CheckoutBean;
import com.smart.dubai.book.service.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookService service;

	@Test
	public void test_checkoutBooks(){
		service.saveBook(getBook());
		Double price = service.checkoutBooks(getCheckoutBean());
		Assert.assertTrue(price>1);
	}

	@Test
	public void test_checkoutBooksFail(){
		service.saveBook(getBook());
		Double price = service.checkoutBooks(getCheckoutBeanFail());
		Assert.assertNull(price);
	}

	@Test
	public void test_checkoutBooksWithoutDiscount(){
		service.saveBook(getBook());
		Double price = service.checkoutBooks(getCheckoutBeanWithPromo());
		Double expected = getBook().getPrice().doubleValue();
		Assert.assertEquals(price,expected);
	}

	@Test
	public void test_checkoutBooksWithDiscount(){
		service.saveBook(getBook());
		Double price = service.checkoutBooks(getCheckoutBean());
		Double expected = 18.0;
		Assert.assertEquals(price, expected);
	}

	@Test
	public void test_saveBook(){
		Book book = service.saveBook(getBook());
		Assert.assertEquals(book.getName(), getBook().getName());
	}

	@Test
	public void test_findBookById(){
		Book book = service.saveBook(getBook());
		Book TestBook = service.findBookById(book.getId());
		Assert.assertEquals(book.getName(), TestBook.getName());
	}

	@Test
	public void test_updateBook(){
		Book book = service.saveBook(getBook());
		book.setName("Test2");
		Book TestBook = service.updateBook(book);
		Assert.assertEquals(book.getName(), TestBook.getName());
	}

	@Test
	public void test_findAll(){
		service.saveBook(getBook());
		List<Book> books = service.findAll();
		Assert.assertTrue(books.size()>=1);
	}

	private Book getBook(){
		Book book = new Book();
		book.setName("TestBook");
		book.setPrice(new BigDecimal(20.0));
		book.setIsbn("TestISBN");
		book.setDescription("TestDescription");
		book.setType("FICTION");
		book.setAuthor("TestAuthor");
		return book;
	}

	private CheckoutBean getCheckoutBean(){
		List<BookBean> list = new ArrayList<>();
		CheckoutBean bean = new CheckoutBean();
		BookBean bookBean = new BookBean();
		bookBean.setId(100l);
		bookBean.setName("TestBook");
		list.add(bookBean);
		bean.setBooks(list);
		bean.setPromotionCode("PROMO CODE");
		return bean;
	}

	private CheckoutBean getCheckoutBeanFail(){
		List<BookBean> list = new ArrayList<>();
		CheckoutBean bean = new CheckoutBean();
		BookBean bookBean = new BookBean();
		bookBean.setId(110l);
		bookBean.setName("TestBook");
		list.add(bookBean);
		bean.setBooks(list);
		bean.setPromotionCode("PROMO CODE111");
		return bean;
	}
	private CheckoutBean getCheckoutBeanWithPromo(){
		List<BookBean> list = new ArrayList<>();
		CheckoutBean bean = new CheckoutBean();
		BookBean bookBean = new BookBean();
		bookBean.setId(100l);
		bookBean.setName("TestBook");
		list.add(bookBean);
		bean.setBooks(list);
		return bean;
	}

}
