<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo interceptor</title>
</head>
<body>
    <%
        System.out.println("Template rendering");
        System.out.println("My blog is : " + request.getAttribute("blog"));
        System.out.println("Author : " + request.getAttribute("author"));
        System.out.println("------------------------------------------");
    %>
    <h1 style="text-align: center">${blog}</h1>
    <h1 style="text-align: center">${author}</h1>
</body>
</html>
