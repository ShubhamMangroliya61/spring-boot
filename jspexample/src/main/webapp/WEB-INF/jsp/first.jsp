<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    System.out.println("Aditya");
    BookService bokService = new BookService();
    System.out.println(bookService.getAllBooks());
%>

<html>
  <head>
    <title>View Books</title>
  </head>
  <body>
    <table>
      <thead>
        <tr>
          <th>ISBN</th>
          <th>Name</th>
          <th>Author</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${book.isbn}</td>
          <td>${book.name}</td>
          <td>${book.author}</td>
          <td>${book}</td>

        </tr>
      </tbody>
    </table>
  </body>
</html>
