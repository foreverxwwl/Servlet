package dao.impl;

import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * 点击删除
     * @param id
     */
    @Override
    public void delUser(String id){
        //1.定义SQL语句
        String sql = "delete from user where id = ?";
        //2.执行sql
        template.update(sql, id);
    }

    /**
     * 点击修改
     * @param updateUser
     */
    @Override
    public void updateUsers(User updateUser) {
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql, updateUser.getName(),updateUser.getGender(),updateUser.getAge(),updateUser.getAddress(),updateUser.getQq(),updateUser.getEmail(),updateUser.getId());
    }

    /**
     * 通过id查询
     * @param id
     * @return User
     */
    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    /**
     * 分页查询
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<User> findUserByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());

    }

    /**
     * 查询记录数
     * @return int
     * @param condition
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

}
