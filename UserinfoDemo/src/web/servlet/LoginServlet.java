package web.servlet;

import domain.User;
import org.springframework.beans.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @outhor li
 * @create 2019-10-17 0:01
 * 用户登录
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        /**
         * 2.判断验证码
         */
        //1.获取生成的验证码
        HttpSession session = request.getSession();
        String randomText = (String)session.getAttribute("randomText");
        //2.获取用户输入验证码
        String verifycode = request.getParameter("verifycode");
        //3.判断
        if(!randomText.equalsIgnoreCase(verifycode)){
            //验证码输入错误
            //存入错误信息
            request.setAttribute("Login_msg","验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        }
        /**
         * 2.判断用户名密码
         */
        //4.封装user对象
        User loginUser = new User();
        loginUser.setUsername(request.getParameter("username"));
        loginUser.setPassword(request.getParameter("password"));
        //5.调用service查询
        UserService userService = new UserServiceImpl();
        User user = new User();
        user = userService.login(loginUser);
        //6.判断是否正确
        if (user == null){
            //账号或密码输入错误
            //存入错误信息
            request.setAttribute("Login_msg","账号或密码错误");
            //转发到登录页面
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        }else {
            //登录成功
            //将用户存入session
            session.setAttribute("user", loginUser);
            //跳转到查询页面
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
