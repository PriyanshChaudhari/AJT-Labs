<%@ page import="ajt.assign05.CurrentTimeServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! CurrentTimeServlet cts = new CurrentTimeServlet(); %>
<html>
<head>
    <title>Current Time</title>
</head>
<body>
<h1>
    <% cts.doGet(request, response); %>
</h1>
</body>
</html>