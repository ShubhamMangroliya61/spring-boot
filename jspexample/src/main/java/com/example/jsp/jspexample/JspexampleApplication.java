package com.example.jsp.jspexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.jsp.jspexample")
public class JspexampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(JspexampleApplication.class, args);
  }
}
