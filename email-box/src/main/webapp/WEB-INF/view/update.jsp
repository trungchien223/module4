<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update Mail Configuration</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
</head>
<body class="container mt-4">

<h2>Update Mail Settings</h2>

<form:form action="/mail/update" modelAttribute="mailConfig" method="post">
  <div class="mb-3">
    <label for="language">Language:</label>
    <form:select path="language" items="${languages}" cssClass="form-select"/>
  </div>

  <div class="mb-3">
    <label for="pageSize">Page Size:</label>
    <form:select path="pageSize" items="${pageSizes}" cssClass="form-select"/>
  </div>

  <div class="mb-3 form-check">
    <form:checkbox path="spamFilter" cssClass="form-check-input"/>
    <label class="form-check-label">Enable Spam Filter</label>
  </div>

  <div class="mb-3">
    <label for="signature">Signature:</label>
    <form:textarea path="signature" rows="3" cssClass="form-control"/>
  </div>

  <button type="submit" class="btn btn-primary">Save</button>
</form:form>

</body>
</html>
