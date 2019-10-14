<%--
  Created 4by IntelliJ IDEA.
  User: lenovo
  Date: 2019/10/6
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>login</title>
    <script>
      window.onload = function(){
        //1.获取图片对象
        var img = document.getElementById("checkCode");
        var word = document.getElementById("change");
        //2.绑定单击事件
        img.onclick = function(){
          //加时间戳
          var date = new Date().getTime();

          img.src = "VerifyCodeServlet?"+date;
          word.src = "VerifyCodeServlet?"+date;
        }
      }

    </script>
  </head>
  <body>
  <form action="/loginServlet" method="post">
    用户名:<input type="text" name="username"> <br>
    密码:<input type="password" name="password"><br>
    验证码:<input type="text" name="verifycode">
    <img id="checkCode" src="/VerifyCodeServlet" />
    <a id="change" href="">看不清换一张？</a><br>

    <input type="submit" value="登录">
  </form>

<%--  <div><%=request.getAttribute("userError") == null ? "":request.getAttribute("userError")%></div>--%>
<%--  <div><%=request.getAttribute("verifyError")== null ? "":request.getAttribute("verifyError")%%></div>--%>
  </body>
</html>
