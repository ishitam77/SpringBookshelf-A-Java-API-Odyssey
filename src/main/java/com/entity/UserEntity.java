package com.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity 
public class UserEntity {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	private String name;
	private String emailId;
	private String occupation;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true)
	private List<BookEntity> books;

	
	public UserEntity() {
		super();
	}
	public UserEntity(String emailId,String name,String occupation) {
		super();
	
		this.name = name;
		this.emailId = emailId;
		this.occupation=occupation;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public List<BookEntity> getBooks() {
		return books;
	}
	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", occupation=" + occupation
				+ ", books=" + books + "]";
	}

}
