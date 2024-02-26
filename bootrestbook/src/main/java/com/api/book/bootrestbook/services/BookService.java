package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.Dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  //   static ArrayList<Book> listOfBooks = new ArrayList<>();

  //   static {
  //     listOfBooks.add(new Book(1, "ABC", "JAVA Core"));
  //     listOfBooks.add(new Book(2, "DEF", "Spring Core"));
  //     listOfBooks.add(new Book(3, "GHI", "Spring MVC"));
  //   }

  public ArrayList<Book> getAllBooks() {
    ArrayList<Book> listOfBooks = (ArrayList<Book>) bookRepository.findAll();
    return listOfBooks;
  }

  public Book getBookbyId(int bookId) {
    Book bookWithBookId = null;
    // bookWithBookId =
    //   listOfBooks
    //     .stream()
    //     .filter(book -> book.getId() == bookId)
    //     .findFirst()
    //     .get();

    bookWithBookId = bookRepository.findById(bookId);

    return bookWithBookId;
  }

  public Book addBook(Book book) {
    // listOfBooks.add(book);
    Book savedBook = bookRepository.save(book);
    return savedBook;
  }

  public void deleteBook(int bookId) {
    // List<Book> remainingBooks = listOfBooks
    //   .stream()
    //   .filter(book -> book.getId() != bookId)
    //   .toList();
    // listOfBooks = new ArrayList<>(remainingBooks);

    bookRepository.deleteById(bookId);
  }

  public Book updateBookById(int bookId, Book book) {
    // List<Book> newList = listOfBooks
    //   .stream()
    //   .map(b -> {
    //     if (b.getId() == bookId) {
    //       b.setAuther(book.getAuther());
    //       b.setTitle(book.getTitle());
    //     }
    //     return b;
    //   })
    //   .collect(Collectors.toList());
    // listOfBooks = new ArrayList<>(newList);

    book.setId(bookId);
    bookRepository.save(book);

    Book updatedBook = this.getBookbyId(bookId);
    return updatedBook;
  }
}
