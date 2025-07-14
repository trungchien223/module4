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
  <c:otherwise>
    <p style="color:red">Không tìm thấy từ trong từ điển!</p>
  </c:otherwise>
</c:choose>
<a href="/translate">Quay lại</a>
</body>
</html>
