package web.filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @outhor li
 * @create 2019-10-23 11:08
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转化
        HttpServletRequest request = (HttpServletRequest) req;
        //1.获取资源请求路径
        String uri = request.getRequestURI();
        //2.设置放行路径，要将css，js，图片，验证码等资源也放行
        if (uri.contains("/Login.jsp") || uri.contains("/loginServlet") || uri.contains("/verifyCodeServlet") || uri.contains("/verificationCode/") || uri.contains("/css/") || uri.contains("/js/") ||uri.contains("/fonts/")){
            chain.doFilter(req, resp);
        }else {
            Object user = request.getSession().getAttribute("user");
            //如果不包含，则验证是否登录
            if (user != null){
                //有登录信息，放行
                chain.doFilter(req, resp);
            }else {
                //跳转登录页面
                request.setAttribute("Login_msg","没有登录，请先登录");
                request.getRequestDispatcher("/Login.jsp").forward(request,resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
