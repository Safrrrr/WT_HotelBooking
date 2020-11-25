<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>

<div>
    <h1>Sign In</h1>
</div>

<div>
    <%
        if (request.getAttribute("userName") != null)
            out.println("<p>Вы вошли под именем '" + request.getAttribute("userName") + "'</p>");
    %>
    <div>
        <form method="post">
            <label>Login:
                <input type="text" name="login"><br />
            </label>
            <label>Password:
                <input type="password" name="pass"><br />
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>
