package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getBooks() {
    List<Book> listOfBooks = bookService.getAllBooks();
    if (listOfBooks.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.of(Optional.of(listOfBooks));
  }

  @GetMapping("/books/{bookId}")
  public Book getMethodName(@PathVariable("bookId") int id) {
    return bookService.getBookbyId(id);
  }

  @PostMapping("/books")
  public Book postMethodName(@RequestBody Book book) {
    Book addedBook = bookService.addBook(book);
    return addedBook;
  }

  @DeleteMapping("/books/{bookId}")
  public Book deleteBook(@PathVariable("bookId") int bookId) {
    Book deletedBook = bookService.getBookbyId(bookId);
    bookService.deleteBook(bookId);
    return deletedBook;
  }

  @PutMapping("/books/{bookId}")
  public Book putMethodName(
    @PathVariable("bookId") int bookId,
    @RequestBody Book book
  ) {
    Book updatedBook = null;
    updatedBook = bookService.updateBookById(bookId, book);
    return updatedBook;
  }
}
