package com.api.book.bootrestbook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int authorId;

  String name;

  public Author(int authorId, String name) {
    this.authorId = authorId;
    this.name = name;
  }

  public Author() {
    super();
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Author [authorId=" + authorId + ", name=" + name + "]";
  }
}
