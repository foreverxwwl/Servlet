package jedisTest.test;

import jedisTest.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @outhor li
 * @create 2019-10-30 23:19
 * jedis学习
 */
public class jedisTest {
    /**
     * string 数据结构操作
     */
    @Test
    public void test2() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作
        //存储
        jedis.set("username", "lihaobo");
        jedis.set("username2", "zhanghaohua");
        //获取
        System.out.println(jedis.get("username"));
        //可以使用setex()方法存储可以指定过期时间的 key value
        jedis.setex("activeCode", 10, "1345");//将activecode：1345键值对存入redis，并且20秒后自动删除该键值对
        //关闭
        jedis.close();
        /*
        lihaobo
         */
    }

    /**
     * hash 数据结构操作
     */
    @Test
    public void test3() {
        //1. 获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值 "localhost",6379端口
        // 2. 操作
        // 存储hash
        jedis.hset("lihaobo", "password", "123");
        jedis.hset("lihaobo", "age", "23");
        jedis.hset("zhanghaohua", "password", "456");
        jedis.hset("zhanghaohua", "age", "22");
        //获取hash
        System.out.println(jedis.hget("lihaobo", "password"));
        System.out.println(jedis.hget("zhanghaohua", "password"));

        //获取hash中的map集合
        Map<String, String> lihaobo = jedis.hgetAll("lihaobo");
        System.out.println(lihaobo);
        Set<String> keySet = lihaobo.keySet();
        for (String key :
                keySet) {
            String s = lihaobo.get(key);
            System.out.println(s);
        }
        //关闭
        jedis.close();
        /*
        123
        456
        {password=123, age=23}
        123
        23
         */
    }

    /**
     * list 数据结构操作
     */
    @Test
    public void test4() {
        Jedis jedis = new Jedis("localhost", 6379);
        //2. 操作
        // list 存储
        jedis.lpush("mylist","a","b","c");//从左边存
        jedis.rpush("mylist","a","b","c");//从右边存

        //list范围获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        //list弹出
        String mylist1 = jedis.lpop("mylist");
        String mylist2 = jedis.rpop("mylist");
        System.out.println(mylist1);
        System.out.println(mylist2);

        //list范围获取
        mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        jedis.close();
        /*
        [c, b, a, a, b, c]
        c
        c
        [b, a, a, b]
        */

    }

    /**
     * set 数据结构操作
     */
    @Test
    public void test5(){
        Jedis jedis = new Jedis();

        jedis.sadd("myset", "java","c/c++", "python");

        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        jedis.close();
        /*
        [python, java, c/c++]
         */
    }

    /**
     * sortedset 数据结构操作
     */
    @Test
    public void test6(){
        Jedis jedis = new Jedis();

        // sortedset 存储
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",30,"后裔");
        jedis.zadd("mysortedset",55,"孙悟空");
        //获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);

        System.out.println(mysortedset);

        jedis.close();
        /*
        [亚瑟, 后裔, 孙悟空]
         */
    }

    /**
     * jedis连接池使用
     */
    @Test
    public void test7(){
        //创建配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        //创建jedis连接对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);
        //获取连接
        Jedis jedis = jedisPool.getResource();
        //使用
        jedis.set("username", "lihaob");
        System.out.println(jedis.get("username"));
        //关闭
        jedis.close();
    }

    /**
     * jedis连接池工具类使用
     */
    @Test
    public void test8(){

        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //3. 使用
        jedis.set("hello","world");
        System.out.println(jedis.get("hello"));
        //4. 关闭 归还到连接池中
        jedis.close();

    }

}

