package dao.impl;

import dao.impl.UserDao;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @outhor li
 * @create 2019-10-15 15:40
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有
     * @return List<User>
     */
    @Override
    public List<User> findAll(){
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    /**
     * 添加用户
     * @param addUser user对象，用户输入页面返回的对象
     * @return void
     */
    @Override
    public void addUsers(User addUser) throws SQLException {


        try{
            //1.sql 语句
            String sql = "insert into user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
            //2.调用update方法
            template.update(sql, addUser.getName(),addUser.getGender(),addUser.getAge(),addUser.getAddress(),addUser.getQq(),addUser.getEmail());
        } catch (DataAccessException e){
            e.printStackTrace();
        }

    }

    /**
     * 用户登录
     * @param loginUser
     * @return User
     */
    @Override
    public User login(User loginUser) {
        try{
            //1.sql 语句
            String sql = "select * from user where username = ? and password = ?";
            //2.调用query方法
            //通过query方法查询数据库，通过BeanPropertyRowMapper实现类完成数据到JavaBean的自动封装，将查询结果封装到一个User对象中并返回
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
