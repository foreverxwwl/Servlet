package web.filter;

import sun.security.krb5.internal.tools.Klist;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @outhor li
 * @create 2019-10-24 18:04
 * 过滤敏感词
 */
@WebFilter("/*")
public class SensitiveWordFilter implements Filter {
    //存放敏感词集合
    private List<String> list = new ArrayList<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //2.增强getParameter方法
                //判断是否为getParameter方法
                if (method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String)method.invoke(req, args);
                    if (value != null){
                        for ( String str: list) {
                            if (value.contains(str)){
                                value = value.replaceAll(str, "**");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        try {
        //获取文件真实路径
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
        //读取文件
        BufferedReader br = new BufferedReader(new FileReader(realPath));
        //将文件每一行加载到list中
        String line = null;
        while ((line = br.readLine()) != null){
            list.add(line);
        }
            System.out.println(list);
        br.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
