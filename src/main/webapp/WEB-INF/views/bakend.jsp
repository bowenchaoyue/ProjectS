<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/9
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String serverName = "47.96.153.197";
    String basePath = request.getScheme() + "://"
            + serverName + path + "/";
    String homePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>首页</title>
</head>
<frameset rows="100,*" cols="*" scrolling="No" framespacing="0"
          frameborder="no" border="0"> <frame src="head.html"
                                              name="headmenu" id="mainFrame" title="mainFrame"><!-- 引用头部 -->
    <!-- 引用左边和主体部分 --> <frameset rows="100*" cols="220,*" scrolling="No"
                                 framespacing="0" frameborder="no" border="0"> <frame
            src="left.html" name="leftmenu" id="mainFrame" title="mainFrame">
        <frame src="main.html" name="main" scrolling="yes" noresize="noresize"
               id="rightFrame" title="rightFrame"></frameset></frameset>
</html>