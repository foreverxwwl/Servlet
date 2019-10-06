package web;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @outhor li
 * @create 2019-10-06 1:12
 * 失败逻辑
 */
@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取request域中的user对象
        User user = (User) req.getAttribute("user");
        if (user != null) {
            //设置编码
            resp.setContentType("text/html;charset=utf-8");
            //输出
            resp.getWriter().write("用户" + user.getUsername() + "登录成功");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
