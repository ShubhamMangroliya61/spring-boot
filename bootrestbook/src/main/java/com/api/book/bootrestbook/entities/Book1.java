package com.api.book.bootrestbook.entities;

public class Book1 {

  private int id;
  private User auther;
  private String title;

  public Book1(int id, User auther, String title) {
    this.id = id;
    this.auther = auther;
    this.title = title;
  }

  public Book1() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getAuther() {
    return auther;
  }

  public void setAuther(User auther) {
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
    return "Book1 [id=" + id + ", auther=" + auther + ", title=" + title + "]";
  }
}
