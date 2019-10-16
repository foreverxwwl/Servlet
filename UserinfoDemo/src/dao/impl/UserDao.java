package dao.impl;

import domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @outhor li
 * @create 2019-10-15 15:47
 */
public interface UserDao {
    public List<User> findAll();
    public void addUsers(User addUser) throws SQLException;
}
