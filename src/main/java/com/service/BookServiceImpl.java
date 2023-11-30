package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDao;
import com.entity.BookEntity;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<BookEntity> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public BookEntity fetchBookByIsbn(int isbn) {
        return bookDao.findByIsbn(isbn);
    }

    @Override
    public void saveBook(BookEntity bookEntity) {
        bookDao.save(bookEntity);
    }

    @Override
    public void deleteBookById(int bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    public void updateBook(BookEntity updatedBook) {
        BookEntity existingBook = bookDao.findByBookId(updatedBook.getBookId());
        if (existingBook != null) {
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setType(updatedBook.getType());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setAuthor(updatedBook.getAuthor());
            bookDao.save(existingBook);
        }
    }

}
