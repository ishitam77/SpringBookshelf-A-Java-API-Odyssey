package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class BookEntity {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
	private int isbn;
	private String title;
	private String type;
	private int price;
	private String author;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "fk_user_id") // This should match the name of the column in the BookEntity table
    private UserEntity user;
	
	
	public BookEntity() {
		super();
	}
	
	public BookEntity(int isbn, String title, String type, int price, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.type = type;
		this.price = price;
		this.author = author;
	}
	

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BookEntity [bookId=" + bookId + ", isbn=" + isbn + ", title=" + title + ", type=" + type + ", price="
				+ price + ", author=" + author + "]";
	}
	
	
}
