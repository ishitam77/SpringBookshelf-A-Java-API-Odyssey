package com.service;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.BookEntity;
import com.entity.UserEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

            // Create a map of existing books for efficient lookup
            Map<Integer, BookEntity> existingBooksMap = existingUser.getBooks().stream()
                    .collect(Collectors.toMap(BookEntity::getBookId, Function.identity()));

            // Update and add books from the updated user
            for (BookEntity updatedBook : updatedUser.getBooks()) {
                if (updatedBook.getBookId() == 0) {
                    // Assign a new bookId if it's not specified (assuming 0 means not specified)
                    updatedBook.setBookId(getNextBookId(existingUser.getBooks()));
                }

                // If the book exists in the existing user's books, update it
                if (existingBooksMap.containsKey(updatedBook.getBookId())) {
                    BookEntity existingBook = existingBooksMap.get(updatedBook.getBookId());
                    existingBook.setIsbn(updatedBook.getIsbn());
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setType(updatedBook.getType());
                    existingBook.setPrice(updatedBook.getPrice());
                    existingBook.setAuthor(updatedBook.getAuthor());
                } else {
                    // If the book doesn't exist, add it to the user's books
                    updatedBook.setUser(existingUser);
                    existingUser.getBooks().add(updatedBook);
                }
            }

            // Remove books that are not in the updated list
            existingUser.getBooks().removeIf(book -> !updatedUser.getBooks().contains(book));

            userDao.save(existingUser);

            // Add logging statements
            System.out.println("Updated User: " + existingUser);
            System.out.println("Updated Books: " + existingUser.getBooks());
        }
    }
    private int getNextBookId(List<BookEntity> books) {
        return books.stream()
                .mapToInt(BookEntity::getBookId)
                .max()
                .orElse(0) + 1;
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

                    // Save the user within the same transaction
                    userDao.save(existingUser);

                    // Add logging statements
                    System.out.println("Updated User: " + existingUser);
                    System.out.println("Updated Book: " + existingBook);
                } else {
                    // Handle the case where the book is not found
                    System.out.println("Book not found for user with ID: " + userId);
                }
            } else {
                // Handle the case where the user is not found
                System.out.println("User not found with ID: " + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, e.g., log or throw a custom exception
        }
    }

    
}

