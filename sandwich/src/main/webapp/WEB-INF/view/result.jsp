<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Selected Condiments</title>
</head>
<body>
<h1>You selected:</h1>
<c:choose>
  <c:when test="${not empty selectedCondiments}">
    <ul>
      <c:forEach var="item" items="${selectedCondiments}">
        <li>${item}</li>
      </c:forEach>
    </ul>
  </c:when>
  <c:otherwise>
    <p><em>No condiments selected.</em></p>
  </c:otherwise>
</c:choose>

<br>
<a href="/appsandwich">⬅ Back to Form</a>
</body>
</html>
