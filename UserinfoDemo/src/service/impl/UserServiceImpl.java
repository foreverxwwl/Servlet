package service.impl;

import dao.impl.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

/**
 * @outhor li
 * @create 2019-10-15 15:36
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 查询所有
     * @return List<User>
     */
    @Override
    public List<User> findAll(){
        return dao.findAll();
    }

    /**
     * 添加用户
     * @param addUser
     * @throws SQLException
     */
    @Override
    public void addUsers(User addUser) throws SQLException{
        dao.addUsers(addUser);
    }

    /**
     * 用户登录
     * @param loginUser
     * @return User
     */
    @Override
    public User login(User loginUser) {
        return dao.login(loginUser);
    }

    /**
     * 点击删除
     * @param id
     */
    @Override
    public void delUser(String id) {
        dao.delUser(id);
    }
}
