package web.servlet;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @outhor li
 * @create 2019-10-19 14:53
 * 根据id查找
 */
@WebServlet("/findUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取页面元素
        String id = request.getParameter("id");
        //通过id查找值user
        User user = new User();
        UserService userService = new UserServiceImpl();
        user = userService.findUserById(id);
        //将user存入request
        request.setAttribute("user", user);
        //转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
