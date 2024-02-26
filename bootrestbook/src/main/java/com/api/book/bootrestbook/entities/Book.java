package com.api.book.bootrestbook.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @OneToOne(cascade = CascadeType.ALL)
  private Author auther;

  private String title;

  public Book(int id, Author auther, String title) {
    this.id = id;
    this.auther = auther;
    this.title = title;
  }

  public Book() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Author getAuther() {
    return auther;
  }

  public void setAuther(Author auther) {
    this.auther = auther;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", auther=" + auther + ", title=" + title + "]";
  }
}
