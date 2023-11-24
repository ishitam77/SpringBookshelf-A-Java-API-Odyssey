package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.BookEntity;
import com.entity.UserEntity;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private BookService bookService;

    @Override
    public List<UserEntity> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserEntity findUserById(int userId) {
        return userDao.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userDao.save(userEntity);
    }

    @Override
    public void deleteUserById(int userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void updateUser(UserEntity updatedUser) {
        // Find the existing user by ID
        UserEntity existingUser = userDao.findById(updatedUser.getUserId()).orElse(null);

        if (existingUser != null) {
            // Update the user properties
            existingUser.setName(updatedUser.getName());
            existingUser.setEmailId(updatedUser.getEmailId());
            existingUser.setOccupation(updatedUser.getOccupation());
            userDao.save(existingUser);
        }
    }
  
    @Override
    public void updateBook(BookEntity updatedBook) {
        bookService.updateBook(updatedBook);
    }
    
    @Override
    public void updateBookForUser(int userId, int bookId, BookEntity updatedBook) {
        try {
            // Find the existing user by ID
            UserEntity existingUser = userDao.findById(userId).orElse(null);

            if (existingUser != null) {
                // Find the existing book by ID
                BookEntity existingBook = existingUser.getBooks().stream()
                        .filter(book -> book.getBookId() == bookId)
                        .findFirst()
                        .orElse(null);

                if (existingBook != null) {
                    // Update the book properties
                    existingBook.setIsbn(updatedBook.getIsbn());
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setType(updatedBook.getType());
                    existingBook.setPrice(updatedBook.getPrice());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    userDao.save(existingUser);
                    System.out.println("Updated User: " + existingUser);
                    System.out.println("Updated Book: " + existingBook);
                } else {
                    System.out.println("Book not found for user with ID: " + userId);
                }
            } else {
                System.out.println("User not found with ID: " + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

