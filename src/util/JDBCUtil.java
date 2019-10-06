package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @outhor li
 * @create 2019-10-06 0:12
 * JDBC工具类Durid连接池
 */
public class JDBCUtil {
    /**
     * 创建一个连接池对象
     */
    private static DataSource dataSource;

    /**
     * 加载驱动并初始化连接池对象
     */
    static {
        try {
            //1.使用ClassLoader加载配置文件，获取字节流输入
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //2.加载配置文件
            Properties properties = new Properties();
            properties.load(is);
            //3.初始化连接对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
