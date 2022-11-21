package com.smart.dubai.book.service;

import com.smart.dubai.book.service.entity.Book;
import com.smart.dubai.book.service.entity.Promo;
import com.smart.dubai.book.service.model.BookBean;
import com.smart.dubai.book.service.model.CheckoutBean;
import com.smart.dubai.book.service.repository.BookRepository;
import com.smart.dubai.book.service.repository.PromoRepository;
import com.smart.dubai.book.service.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;


@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookService service;

	@MockBean
	BookRepository bookRepo;

	@MockBean
	PromoRepository promoRepo;

	@Test
	public void test_checkoutBooks(){
		Mockito.when(promoRepo.findByCode("PROMO CODE")).thenReturn(getPromo());
		Mockito.when(bookRepo.findAllById(anyList())).thenReturn(getBooks());
		Double price = service.checkoutBooks(getCheckoutBean());
		Assert.assertTrue(price>1);
	}

	@Test
	public void test_checkoutBooksFail(){
		Mockito.when(promoRepo.findByCode("PROMO CODE")).thenReturn(getPromo());
		//Mockito.when(bookRepo.findAllById(anyList())).thenReturn(getBooks());
		Double price = service.checkoutBooks(getCheckoutBeanFail());
		Assert.assertNull(price);
	}

	@Test
	public void test_checkoutBooksWithoutDiscount(){
		Mockito.when(promoRepo.findByCode("PROMO CODE")).thenReturn(getPromo());
		Mockito.when(bookRepo.findAllById(anyList())).thenReturn(getBooks());
		Double price = service.checkoutBooks(getCheckoutBeanWithoutPromo());
		Double expected = getBook().getPrice().doubleValue();
		Assert.assertEquals(expected,price);
	}

	@Test
	public void test_checkoutBooksWithDiscount(){
		Mockito.when(promoRepo.findByCode("PROMO CODE")).thenReturn(getPromo());
		Mockito.when(bookRepo.findAllById(anyList())).thenReturn(getBooks());
		Double price = service.checkoutBooks(getCheckoutBean());
		Double expected = 18.0;
		Assert.assertEquals(expected,price);
	}

	@Test
	public void test_saveBook(){
		Mockito.when(bookRepo.save(anyObject())).thenReturn(getBook());
		Book book = service.saveBook(getBook());
		Assert.assertEquals(book.getName(), getBook().getName());
	}

	@Test
	public void test_findBookById(){
		Mockito.when(bookRepo.findById(anyObject())).thenReturn(getOptionalBook());
		Book TestBook = service.findBookById(getBook().getId());
		Assert.assertEquals(getBook().getName(), TestBook.getName());
	}

	@Test
	public void test_updateBook(){
		Mockito.when(bookRepo.save(anyObject())).thenReturn(getBook());
		Mockito.when(bookRepo.findById(anyObject())).thenReturn(getOptionalBook());
		Book book = service.saveBook(getBook());
		book.setName("Test2");
		Book TestBook = service.updateBook(book);
		Assert.assertEquals(book.getName(), TestBook.getName());
	}

	@Test
	public void test_findAll(){
		Mockito.when(bookRepo.findAll()).thenReturn(getBooks());
		List<Book> books = service.findAll();
		Assert.assertTrue(books.size()>=1);
	}

	private Optional<Book> getOptionalBook(){
		Book book = new Book();
		book.setId(50l);
		book.setName("TestBook");
		book.setPrice(new BigDecimal(20.0));
		book.setIsbn("TestISBN");
		book.setDescription("TestDescription");
		book.setType("FICTION");
		book.setAuthor("TestAuthor");
		Optional<Book> result = Optional.ofNullable(book);
		return result;
	}
	private Book getBook(){
		Book book = new Book();
		book.setId(50l);
		book.setName("TestBook");
		book.setPrice(new BigDecimal(20.0));
		book.setIsbn("TestISBN");
		book.setDescription("TestDescription");
		book.setType("FICTION");
		book.setAuthor("TestAuthor");
		return book;
	}
	private List<Book> getBooks(){
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setName("TestBook");
		book.setPrice(new BigDecimal(20.0));
		book.setIsbn("TestISBN");
		book.setDescription("TestDescription");
		book.setType("FICTION");
		book.setAuthor("TestAuthor");
		books.add(book);
		return books;
	}

	private Promo getPromo(){
		Promo promo = new Promo();
		promo.setCode("PROMO CODE1");
		promo.setFictionPercentage(10d);
		promo.setComicPercentage(0d);
		return promo;
	}


	private CheckoutBean getCheckoutBean(){
		List<BookBean> list = new ArrayList<>();
		CheckoutBean bean = new CheckoutBean();
		BookBean bookBean = new BookBean();
		bookBean.setId(500l);
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
		bookBean.setId(500l);
		bookBean.setName("TestBook");
		list.add(bookBean);
		bean.setBooks(list);
		bean.setPromotionCode("PROMO CODE");
		return bean;
	}

	private CheckoutBean getCheckoutBeanWithoutPromo(){
		List<BookBean> list = new ArrayList<>();
		CheckoutBean bean = new CheckoutBean();
		BookBean bookBean = new BookBean();
		bookBean.setId(500l);
		bookBean.setName("TestBook");
		list.add(bookBean);
		bean.setBooks(list);
		return bean;
	}

}
