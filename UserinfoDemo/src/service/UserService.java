package service;

import domain.Page;
import domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    /**
     * 点击删除
     * @param id
     */
    public void delUser(String id);

    /**
     * 点击修改
     * @param updateUser
     */
    public void updateUsers(User updateUser);

    /**
     * 通过id查找
     * @param id
     * @return User
     */
    User findUserById(String id);

    /**
     * 删除选中
     * @param ids
     */
    void delSelectUser(String[] ids);

    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    Page<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
