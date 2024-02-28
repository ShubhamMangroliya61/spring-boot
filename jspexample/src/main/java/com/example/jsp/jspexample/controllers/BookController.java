package com.example.jsp.jspexample.controllers;

import com.example.jsp.jspexample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @RequestMapping(value = "/books", method = RequestMethod.GET)
  public String getBook(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
    model.addAttribute("books2", bookService.getAllBooks());
    return "view-books";
  }
}
