<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<div>
    <h1>Sign Up</h1>
</div>
<div>
    <%
        if (request.getAttribute("userName") != null) {
            out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
        }
    %>
    <div>


        <form method="post">
            <label>Name:
                <input type="text" name="name"><br />
            </label>
            <label>SurName:
                <input type="text" name="surname"><br />
            </label>
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
