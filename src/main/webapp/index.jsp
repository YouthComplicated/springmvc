<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
</body>
<img src="aaa.png">
<form action="/upload/part" method="post" enctype="multipart/form-data">
    <input type="file" class="file1" name="picture" />
    <button type="submit" class="but1">上传</button>
</form>
</html>