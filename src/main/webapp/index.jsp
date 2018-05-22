<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/11
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  您好,ServletDemo-004
  <%
    String contextPath = request.getSession().getServletContext().getRealPath("/");
    out.println(contextPath);
  %>
  </body>
</html>
