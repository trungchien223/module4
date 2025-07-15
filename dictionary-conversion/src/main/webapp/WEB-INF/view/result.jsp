<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Kết quả tra cứu</title>
</head>
<body>
<h2>Kết quả tra cứu</h2>
<p>Từ: <b>${word}</b></p>
<c:choose>
  <c:when test="${not empty result}">
    <p>Nghĩa tiếng Việt: <b>${result}</b></p>
  </c:when>
</c:choose>
<c:if test="${not empty message}">
  <p style="color:red">${message}</p>
</c:if>
<a href="/translate">Quay lại</a>
</body>
</html>
