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
    @Override
    public List<User> findAll(){
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    /**
     * 添加用户过程操作数据库方法
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
}
