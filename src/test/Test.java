package test;

import dao.UserDao;
import domain.User;

/**
 * @outhor li
 * @create 2019-10-06 0:42
 * 测试数据库连接
 */
public class Test {

    public static void main(String[] args) {
        User loginUser = new User();
        loginUser.setUsername("li");
        loginUser.setPassword("123");

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);
    }

}
