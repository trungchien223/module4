<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Mail Configuration</title>
  <meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<c:if test="${not empty successMessage}">
  <div id="successAlert" class="alert alert-success alert-dismissible fade show mt-3" role="alert">
      ${successMessage}
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  </div>

  <script>
    setTimeout(function () {
      var alert = document.getElementById("successAlert");
      if (alert) {
        alert.classList.remove("show");
        alert.classList.add("fade");
        setTimeout(function () {
          alert.remove();
        }, 500);
      }
    }, 4000);
  </script>
</c:if>

<body class="bg-light">

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-8">

      <div class="card shadow-sm border-0">
        <div class="card-header bg-primary text-white text-center">
          <h4 class="mb-0">Mail Configuration</h4>
        </div>

        <div class="card-body">
          <table class="table table-bordered table-striped">
            <tbody>
            <tr>
              <th style="width: 30%">Language</th>
              <td>${mailConfig.language}</td>
            </tr>
            <tr>
              <th>Page Size</th>
              <td>${mailConfig.pageSize} emails per page</td>
            </tr>
            <tr>
              <th>Spam Filter</th>
              <td>
                <c:choose>
                  <c:when test="${mailConfig.spamFilter}">Enabled</c:when>
                  <c:otherwise>Disabled</c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>Signature</th>
              <td><pre class="mb-0 bg-light p-2 rounded border">${mailConfig.signature}</pre></td>
            </tr>
            </tbody>
          </table>

          <div class="text-end">
            <a href="/mail/edit" class="btn btn-outline-primary">Edit Configuration</a>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>

</body>
</html>
