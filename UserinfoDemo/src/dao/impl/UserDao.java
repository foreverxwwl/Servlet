package dao.impl;

import domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    /**
     * 点击修改
     * @param updateUser
     */
    void updateUsers(User updateUser);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 分页查询
     * @param start
     * @param rows
     * @param condition
     * @return List<User>
     */
    List<User> findUserByPage(int start, int rows, Map<String, String[]> condition);

    /**
     * 查询记录数
     * @param condition
     * @return int
     */
    int findTotalCount(Map<String, String[]> condition);
}
