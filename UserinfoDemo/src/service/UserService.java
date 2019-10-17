package service;

import domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @outhor li
 * @create 2019-10-15 15:35
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     */
    public List<User> findAll();

    /**
     * 添加用户信息
     * @param addUser
     * @throws SQLException
     */
    public void addUsers(User addUser) throws SQLException;

    /**
     * 登录查询
     * @param loginUser
     * @return User
     */
    public User login(User loginUser);
}
