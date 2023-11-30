package com.service;

import java.util.List;

import com.entity.BookEntity;

public interface BookService {

    List<BookEntity> findAllBooks();

    BookEntity fetchBookByIsbn(int isbn);

    void saveBook(BookEntity bookEntity);

    void deleteBookById(int bookid);

    void updateBook(BookEntity updatedBook);
}

