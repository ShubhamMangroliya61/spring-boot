<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Create User</title>
    <link href="
    <c:url value="/css/register.css" />
    " rel="stylesheet" type="text/css">
  </head>
  <body>
    <h1>Create User</h1>
    <form action="registerUser" method="post">
      Name: <input type="text" name="name" /><br />
      Email: <input type="text" name="email" /><br />
      Password: <input type="password" name="password" /><br />
      <input type="submit" value="Create User" />
    </form>
  </body>
</html>
