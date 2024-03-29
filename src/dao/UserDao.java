package dao;

import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @outhor li
 * @create 2019-10-06 0:27
 * 操作数据库user表
 */
public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
         * 登录过程操作数据库方法
         * @param loginUser user对象，用户输入页面返回的对象
         * @return user  user对象，查询的结果对象，没查询到则返回null
         */
        public User login(User loginUser) {

            try{
                //1.sql 语句
                String sql = "select * from users where username = ? and password = ?";
                //2.调用query方法
                //通过query方法查询数据库，通过BeanPropertyRowMapper实现类完成数据到JavaBean的自动封装，将查询结果封装到一个User对象中并返回
                User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
                return user;
            } catch (DataAccessException e){
                e.printStackTrace();
                return null;
            }
    }
    /**
     * 添加用户过程操作数据库方法
     * @param addUser user对象，用户输入页面返回的对象
     * @return void
     */
    public void addUser(User addUser) throws SQLException {


        try{
            //1.sql 语句
            String sql = "insert into users(username,password) values(?,?)";
            //2.调用update方法
            template.update(sql, addUser.getUsername(),addUser.getPassword());
        } catch (DataAccessException e){
            e.printStackTrace();
        }

    }

}


