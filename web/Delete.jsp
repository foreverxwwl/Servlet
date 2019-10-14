<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>删除用户</title>
</head>

<body>
<form  method="post" action="/DeleteUser">
    <div align="center"><strong>
        删除用户</strong><br/>
        <hr/>
        <p>
        </p>
        <p>输入删除的用户名 <input name="username" type="text"/><br/><br/>
            <input type="submit" name="delete" value="删除用户" />
            <br />
            <br />
        </p>
    </div>
</form>
</body>
</html>
