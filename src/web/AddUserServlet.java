package web;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @outhor li
 * @create 2019-10-12 14:31
 * 添加用户信息
 */
@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回上页
        String goBack="<br><a href='javascript:window.history.go(-1);'>返回上页</a>";
        resp.setContentType("text/html;charset=gb2312");
        PrintWriter out = resp.getWriter();

        //获取username
        String username=req.getParameter("username");
        if(username==null||username.equals(""))
        {
            out.print("用户名不能为空！");
            out.print(goBack);
        }
        //获取password
        String password=req.getParameter("password");
        //封装user对象
        User addUser=new User();
        addUser.setUsername(username);
        addUser.setPassword(password);

        //创建数据库操作对象
        UserDao dao = new UserDao();
        try {
            //调用添加方法
            dao.addUser(addUser);
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("添加失败:"+e.getLocalizedMessage());
            out.print(goBack);
            return;
        }
        out.print("添加成功！");
        out.print(goBack);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
