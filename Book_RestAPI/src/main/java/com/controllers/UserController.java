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
import com.entity.UserEntity;
import com.service.BookService;
import com.service.UserService;

@RestController
@RequestMapping("/api_grp1")
public class UserController {

    @Autowired
    private UserService userService;
    

    @GetMapping("/users") // localhost:999/api_grp1/users
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")  //localhost:999/api_grp1/users/1
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        UserEntity user = userService.findUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserEntity userEntity) {
    	 for (BookEntity book : userEntity.getBooks()) {
    	        book.setUser(userEntity);
    	    }
    	userService.saveUser(userEntity);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    

@PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UserEntity updatedUser) {
        try {

            updatedUser.setUserId(id);

 
            userService.updateUser(updatedUser);
            
            // Update book details associated with the user
            for (BookEntity updatedBook : updatedUser.getBooks()) {
                // Set the user for each book
                updatedBook.setUser(updatedUser);
                // Update book details
                userService.updateBook(updatedBook);
            }

            return new ResponseEntity<>("User Updated Successfully!!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("User Not Found!!", HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUserById(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<String> updateBookForUser(
            @PathVariable int userId,
            @PathVariable int bookId,
            @RequestBody BookEntity updatedBook) {
        try {
            userService.updateBookForUser(userId, bookId, updatedBook);
            return new ResponseEntity<>("Book updated for user successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update book for user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


