package com.example.jsp.jspexample.services;

import com.example.jsp.jspexample.models.Book;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  public List<Book> getAllBooks() {
    List<Book> listOfBooks = new ArrayList<>();

    listOfBooks.add(new Book("1234", "XYZ1", "ABC1"));
    listOfBooks.add(new Book("1235", "XYZ2", "ABC2"));
    listOfBooks.add(new Book("1236", "XYZ3", "ABC3"));

    return listOfBooks;
  }
}
