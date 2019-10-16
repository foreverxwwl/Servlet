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

    @Override
    public List<User> findAll(){
        return dao.findAll();
    }
    public void addUsers(User addUser) throws SQLException{
        dao.addUsers(addUser);
    }

}
