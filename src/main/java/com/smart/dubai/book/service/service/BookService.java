package com.smart.dubai.book.service.service;

import com.smart.dubai.book.service.entity.Book;
import com.smart.dubai.book.service.entity.Promo;
import com.smart.dubai.book.service.model.BookBean;
import com.smart.dubai.book.service.model.BookTypes;
import com.smart.dubai.book.service.model.CheckoutBean;
import com.smart.dubai.book.service.repository.BookRepository;
import com.smart.dubai.book.service.repository.PromoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PromoRepository promoRepository;

    public Book saveBook(Book book) {
        log.info("BookService :: saveBook() starts here:");
        return bookRepository.save(book);
    }

    public Book findBookById(Long bookId) {
        log.info("BookService :: findBookById() starts here:");
        Book book = bookRepository.findById(bookId).orElse(null);
        return book;
    }

    public Book updateBook(Book book) {
        log.info("BookService :: updateBook() starts here:");
        Book existingbook = bookRepository.findById(book.getId()).orElse(null);
        log.info("BookService :: updatePrice() isBook exist:"+ existingbook);
        if (existingbook != null) {
            if (book.getName() != null && !book.getName().isEmpty())
                existingbook.setName(book.getName());
            if (book.getPrice() != null)
                existingbook.setPrice(book.getPrice());
            if (book.getIsbn() != null && !book.getIsbn().isEmpty())
                existingbook.setIsbn(book.getIsbn());
            if (book.getType() != null && !book.getType().isEmpty())
                existingbook.setType(book.getType());
            if (book.getAuthor() != null && !book.getAuthor().isEmpty())
                existingbook.setAuthor(book.getAuthor());
            if (book.getDescription() != null && !book.getDescription().isEmpty())
                existingbook.setDescription(book.getDescription());
            return bookRepository.save(existingbook);
        }
        return null;
    }

    public List<Book> findAll() {
        log.info("BookService :: findAll() starts here:");
       return  bookRepository.findAll();
    }

    public Double checkoutBooks(CheckoutBean checkoutBean) {
        log.info("BookService :: checkoutBooks() starts here:");

        Promo promo = null;
        Double afterDiscountFictionBooksPrice =0.0;
        Double afterDiscountComicsBooksPrice = 0.0;
        String promoCheck = checkoutBean.getPromotionCode();

        if(null != promoCheck && !promoCheck.isEmpty())
            promo = promoRepository.findByCode(promoCheck);
        log.info(null != promo ? "Promo code has been found: "+ promo.getCode() : "Promo code not found");
        //List<Book> books =  bookRepository.findAllById(checkoutBean.getBooks().stream().map(x -> x.getId())
          //      .collect(Collectors.toList()));
        List<Book> books = getBooksById(checkoutBean.getBooks());
        if(books.size() > 0) {
            Double totalFictionBooksPrice = books.stream().filter(o -> o.getType().equalsIgnoreCase(BookTypes.FICTION.name()))
                    .mapToDouble(o -> o.getPrice().doubleValue()).sum();
            Double totalComicBooksPrice = books.stream().filter(o -> o.getType().equalsIgnoreCase(BookTypes.COMIC.name()))
                    .mapToDouble(o -> o.getPrice().doubleValue()).sum();

            if (null !=promo && totalFictionBooksPrice != 0) {
                Double fictionDiscoutPrice = (totalFictionBooksPrice * promo.getFictionPercentage()) / 100;
                afterDiscountFictionBooksPrice = totalFictionBooksPrice - fictionDiscoutPrice;
            }else{
                afterDiscountFictionBooksPrice = totalFictionBooksPrice;
            }

            if (null !=promo && totalComicBooksPrice != 0) {
                Double comicDisountPrice = (totalComicBooksPrice * promo.getComicPercentage()) / 100;
                afterDiscountComicsBooksPrice = totalComicBooksPrice - comicDisountPrice;
            }else{
                afterDiscountComicsBooksPrice = totalComicBooksPrice;
            }
        }else{
            return null;
        }
        return afterDiscountFictionBooksPrice+afterDiscountComicsBooksPrice;
    }

    private List<Book> getBooksById(List<BookBean> beans){
        List<Long> ids = new ArrayList<>();
        for(BookBean bean : beans){
            ids.add(bean.getId());
        }
        return bookRepository.findAllById(ids);
    }

    public void deleteBookById(Long bookId) {
        log.info("BookService :: findAll() starts here:");
        Book book = bookRepository.findById(bookId).orElse(null);
        bookRepository.delete(book);
    }
}
