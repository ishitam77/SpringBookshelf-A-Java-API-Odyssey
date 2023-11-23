package com.service;


import com.entity.BookEntity;
import com.entity.UserEntity;
import java.util.List;

public interface UserService {
	
 List<UserEntity> findAllUsers();
 
 UserEntity findUserById(int userId);
 
 void saveUser(UserEntity userEntity);
 
 void deleteUserById(int userId);

void updateUser(UserEntity updatedUser);

void updateBook(BookEntity updatedBook);

void updateBookForUser(int userId, int bookId, BookEntity updatedBook);
 
}
