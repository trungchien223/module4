<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
    <h2>Chuyển đổi USD sang VNĐ</h2>
    <form action="/appconvert/convert" method="post">
        <label>Tỉ giá (VNĐ/USD):</label>
        <input type="number" name="rate" step="0.01" required><br><br>
        <label>Số lượng USD:</label>
        <input type="number" name="usd" step="0.01" required><br><br>
        <button type="submit">Chuyển đổi</button>
    </form>
</body>
</html> 