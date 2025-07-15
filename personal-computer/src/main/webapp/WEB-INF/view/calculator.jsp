<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Calculator</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="card shadow p-4">
    <h2 class="text-center mb-4">Simple Calculator</h2>

    <!-- Form -->
    <form action="calculate" method="post" class="row g-3 justify-content-center">
      <div class="col-md-4">
        <input type="number" step="any" class="form-control" name="num1" value="${param.num1}" placeholder="Enter first number" required />
      </div>
      <div class="col-md-4">
        <input type="number" step="any" class="form-control" name="num2" value="${param.num2}" placeholder="Enter second number" required />
      </div>
      <div class="col-12 text-center">
        <div class="btn-group mt-3" role="group">
          <button type="submit" name="operator" value="+" class="btn btn-primary">+</button>
          <button type="submit" name="operator" value="-" class="btn btn-secondary">−</button>
          <button type="submit" name="operator" value="*" class="btn btn-success">×</button>
          <button type="submit" name="operator" value="/" class="btn btn-danger">÷</button>
        </div>
      </div>
    </form>

    <!-- Result -->
    <c:if test="${not empty result}">
      <div class="alert alert-info mt-4 text-center">
        Kết quả phép ${operation}: <strong>${result}</strong>
      </div>
    </c:if>

    <!-- Error -->
    <c:if test="${not empty error}">
      <div class="alert alert-danger mt-4 text-center">
          ${error}
      </div>
    </c:if>
  </div>
</div>

</body>
</html>
