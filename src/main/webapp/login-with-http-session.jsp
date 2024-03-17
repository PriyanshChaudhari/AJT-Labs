<%--
  Created by IntelliJ IDEA.
  User: priya
  Date: 17-03-2024
  Time: 01:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to searchBook</title>
    <script>
        function handleLogin() {
            var username = document.getElementById('username').value;
            var password = document.getElementById('password').value;

            fetch('login-http-session', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password),
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json(); // parse the response as JSON
                })
                .then(data => {
                    // handle the response data
                    if (data.status === 'success') {
                        window.location.href = 'searchBook.jsp'; // redirect to searchBook.jsp
                    } else if (data.status === 'error') {
                        alert(data.message); // show an alert with the error message
                    }
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
        }
    </script>
</head>
<body>
<h2>Login Page</h2>
<form action="login-http-session" method="post" onsubmit="event.preventDefault();handleLogin()">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <input type="submit" value="Login">
</form>
</body>
</html>
