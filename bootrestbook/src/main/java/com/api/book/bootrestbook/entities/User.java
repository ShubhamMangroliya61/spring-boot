package com.api.book.bootrestbook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
  name = "users",
  uniqueConstraints = @UniqueConstraint(columnNames = { "email" })
)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotBlank
  private String name;

  @NotBlank
  private String email;

  @NotBlank
  private String password;

  public User(int id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public User() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return (
      "User [id=" +
      id +
      ", name=" +
      name +
      ", email=" +
      email +
      ", password=" +
      password +
      "]"
    );
  }
}
