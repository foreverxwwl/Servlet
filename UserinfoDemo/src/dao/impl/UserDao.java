package dao.impl;

import domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @outhor li
 * @create 2019-10-15 15:47
 * 用户操作dao层
 */
public interface UserDao {
    /**
     * 查询所有
     * @return List<User>
     */
    public List<User> findAll();

    /**
     * 添加用户信息
     * @param addUser
     * @throws SQLException
     */
    public void addUsers(User addUser) throws SQLException;

    /**
     * 用户登录
     * @param loginUser
     * @return User
     */
    public User login(User loginUser);

    /**
     * 点击删除
     * @param id
     */
    public void delUser(String id);
}
