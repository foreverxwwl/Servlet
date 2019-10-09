package web.VerifyCodeServlet;

import verificationCode.VerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @outhor li
 * @create 2019-10-07 23:53
 * 验证码输出逻辑
 */

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width=200;
        int height=69;
        //生成对应宽高的初始图片
        BufferedImage verifyImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //调用VerifyCode类方法生成图片
        String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

        //1.获取session
        HttpSession session = req.getSession();
        //2.存储数据
        session.setAttribute("randomText",randomText);

        //将图片输出到页面
        ImageIO.write(verifyImg,"jpg",resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
