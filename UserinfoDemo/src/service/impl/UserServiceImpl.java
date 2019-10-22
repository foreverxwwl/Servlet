package service.impl;

import dao.impl.UserDao;
import dao.impl.UserDaoImpl;
import domain.Page;
import domain.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    /**
     * 点击修改
     * @param updateUser
     */
    @Override
    public void updateUsers(User updateUser) {
        dao.updateUsers(updateUser);
    }

    /**
     * 通过id查找
     * @param id
     * @return User
     */
    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    /**
     * 删除选中
     * @param ids
     */
    @Override
    public void delSelectUser(String[] ids) {
        if (ids.length > 0 && ids != null) {
            for (String id: ids) {
                dao.delUser(id);
            }
        }
    }

    /**
     * 分页查找
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return Page<User>
     */
    @Override
    public Page<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        Page<User> page = new Page<User>();
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //设置当前页码
        page.setCurrentPage(currentPage);
        //设置页面显示条数
        page.setRows(rows);
        //查询总记录数
        int totalCount = dao.findTotalCount(condition);
        page.setTotalCount(totalCount);
        //分页查询
        int start = (currentPage - 1)*rows;
        List<User> list = dao.findUserByPage(start, rows, condition);
        page.setTotalList(list);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows + 1);
        page.setTotalPage(totalPage);
        return page;
    }
}
