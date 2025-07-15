<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sandwich Condiments</title>
</head>
<body>
<h1>Sandwich Condiments</h1>
<form action="save" method="post">
  <c:forEach var="item" items="${condiments}">
    <label><input type="checkbox" name="condiment" value="${item.name}"/> ${item.name}</label>
  </c:forEach>
  <br><br>
  <input type="submit" value="Save"/>
</form>
</body>
</html>
