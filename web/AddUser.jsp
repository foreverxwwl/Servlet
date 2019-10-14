<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>添加用户</title>
</head>

<body>
<form method="post" action="/AddUser">
    <div align="center"><strong>
        添加用户</strong><br/><hr/>
        用户名：  <input name="username" type="text"/>
        <br/>
        <br/>
        密&nbsp;&nbsp;码：
        <input name="password" type="text"/>
        <br />
        <br />
        <input type="submit" value="添加" />
        <input type="reset"  value="取消" />
    </div>
</form>
</body>
</html>

