<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/14/2025
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Tra cứu từ điển Anh - Việt</h2>
<form action="/search" method="post">
    <label>Nhập từ tiếng Anh:</label>
    <input type="text" name="word" required>
    <button type="submit">Tra cứu</button>
</form>
</body>
</html>
