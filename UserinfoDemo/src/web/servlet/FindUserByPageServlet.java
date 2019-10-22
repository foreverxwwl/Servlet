package web.servlet;

import domain.Page;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @outhor li
 * @create 2019-10-20 13:35
 * 分页查找
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("utf-8");
        //1.获取页面参数rows和currentpage
        String rows = request.getParameter("rows");//每页显示条数
        String currentPage = request.getParameter("currentPage");//当前页码
        if (rows == null || "".equals(rows)){
            rows = "5";
        }
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }

        //获取页面分页查询参数map集合
        Map<String, String[]> condition = request.getParameterMap();


        //2.调用servise方法，获取PageBean
        UserService userService = new UserServiceImpl();
        Page<User> page = userService.findUserByPage(currentPage, rows, condition);

        //3.转发到list.jsp页面
        request.setAttribute("page", page);
        request.setAttribute("condition",condition);//将查询条件存入request、
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
