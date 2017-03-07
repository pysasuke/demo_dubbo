package com.py.demo.tool.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/12/15.
 */
public class RedisClient {
    public static JedisPool jedisPool; // 池化管理jedis链接池

    static {
//        Locale locale = new Locale("zh", "CN");
        //读取相关的配置
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis",locale);
        /*
        //	        创建一个默认的ResourceBundle对象
//	        ResourceBundle会查找包redis下的redis.properties的文件
//	        redis是资源的包名，它跟普通java类的命名规则完全一样：
//	        - 区分大小写
//	        - 扩展名 .properties 省略。就像对于类可以省略掉 .class扩展名一样
//	        - 资源文件必须位于指定包的路径之下（位于所指定的classpath中）
//            假如你是在非Web项目中使用，则一定要写资源文件的路径，也就是包路径必须存在。
//            如果是Web项目，不写包路径可以，此时将资源文件放在WEB-INF\classes\目录下就可以。
         */
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        int maxActive = Integer.parseInt(resourceBundle.getString("redis.pool.maxActive"));
        int maxIdle = Integer.parseInt(resourceBundle.getString("redis.pool.maxIdle"));
        int maxWait = Integer.parseInt(resourceBundle.getString("redis.pool.maxWait"));

        String ip = resourceBundle.getString("redis.ip");
        int port = Integer.parseInt(resourceBundle.getString("redis.port"));

        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
//        config.setMaxActive(maxActive);
        //设置最大空闲数
        config.setMaxIdle(maxIdle);
        //设置超时时间
//        config.setMaxWait(maxWait);

        //初始化连接池
        jedisPool = new JedisPool(config, ip, port);
    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key   key
     * @param value value
     * @return
     * @throws Exception
     */
    public static boolean set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key, Object value) {
        Jedis jedis = null;
        try {
            String objectJson = JSON.toJSONString(value);
            jedis = jedisPool.getResource();
            jedis.set(key, objectJson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Object value = jedis.get(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }


    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public static <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
}
