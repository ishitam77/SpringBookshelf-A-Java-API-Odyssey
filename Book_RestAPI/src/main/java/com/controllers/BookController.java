package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.BookEntity;
import com.service.BookService;


@RestController
@RequestMapping("/api_grp2")
public class BookController {

	@Autowired
	BookService bookService;
	

	@GetMapping("/books") // localhost:999/api_grp2/books
	ResponseEntity<List<BookEntity>> fetchAllBooks() {

		List<BookEntity> bookList = bookService.findAllBooks();

		return new ResponseEntity<>(bookList, HttpStatus.OK);

	}

	@GetMapping("/books/{isbn}")//localhost:999/api_grp2/books/12345
	ResponseEntity<?> fetchBook(@PathVariable int isbn){	
		
		BookEntity bookEntity=bookService.fetchBookByIsbn(isbn);
		if(bookEntity !=null) {
		
		return new ResponseEntity<>(bookEntity,HttpStatus.OK);		
	}
		else {
			return new ResponseEntity<>("Book Not Exist!!",HttpStatus.OK);	//ok==200, HTTP status code 200 : successful HTTP request
			
		}
}
	
	@PostMapping("/books")
	public ResponseEntity<String> registerBook(@RequestBody BookEntity bookEntity) {
	    try {
	        // Set the user for each book
	        bookEntity.setUser(bookEntity.getUser());
	        bookService.saveBook(bookEntity);

	        return new ResponseEntity<>("Book Registered Successfully!!", HttpStatus.CREATED);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Failed to register book", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@DeleteMapping("/books/{id}")
	ResponseEntity<?> deleteBook(@PathVariable int id){	
		try {
		bookService.deleteBookById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Book Not Found!!",HttpStatus.OK);
		}
		return new ResponseEntity<>("Book Deleted Successfully!!",HttpStatus.OK);
		}
	
	@PutMapping("/books/{id}")
	ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody BookEntity bookEntity) {
	    try {
	        bookEntity.setBookId(id);
	        bookService.updateBook(bookEntity);
	        return new ResponseEntity<>("Book Updated Successfully!!", HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Book Not Found!!", HttpStatus.NOT_FOUND);
	    }
	}

	
}
