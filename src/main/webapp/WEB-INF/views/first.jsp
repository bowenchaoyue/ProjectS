<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/9
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>资讯信息分类</h1>
    <ul>
        <c:forEach items="${requestScope.category}" var="item">
            <li>${item.name}</li>
        </c:forEach>
    </ul>
</body>
</html>
