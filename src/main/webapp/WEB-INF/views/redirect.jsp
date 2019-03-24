<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Spring MVC页面重定向</h2>
<p>点击下面的按钮将结果重定向到新页面</p>
    <form method="GET" action="./test/redirect">
        <table>
            <tr>
                <td><input type="submit" value="页面重定向1" /></td>
            </tr>
        </table>
    </form>
<p>点击下面的按钮将结果重定向到新页面</p>
<form method="GET" action="./flash/redirect">
    <table>
        <tr>
            <td><input type="submit" value="页面重定向2" /></td>
        </tr>
    </table>
</form>
</body>
</html>
