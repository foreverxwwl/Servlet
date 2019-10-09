package web;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @outhor li
 * @create 2019-10-06 0:57
 * 登录页面处理
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        //2.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String loginRandomText = req.getParameter("verifycode");

        //判断验证码
        //1.获取session
        HttpSession session = req.getSession();
        //2.获取数据
        Object randomText = session.getAttribute("randomText");
        //删除验证码
        session.removeAttribute("randomText");

        if (loginRandomText.toUpperCase().equals(randomText.toString().toUpperCase())){
            //3.封装user对象
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);

            //4.调用login方法在数据库中查询
            UserDao dao = new UserDao();
            User user = dao.login(loginUser);
            System.out.println(user);

            //5.判断user
            if (loginUser == null){
                //2代表用户名或密码错误
                req.setAttribute("userError", "用户名或密码错误");
                //登录失败
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }else {
                //登录成功
                //存储数据
                req.setAttribute("user",loginUser);
                //转发
                req.getRequestDispatcher("/successServlet").forward(req,resp);
            }
        }else {
            //设置错误原因，1代表验证码错误
            req.setAttribute("verifyError", "验证码错误");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
