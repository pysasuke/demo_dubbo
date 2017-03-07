package com.py.demo.tool.redis;

import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.SerializeUtils;

/**
 * Created by DavidWang on 16/8/10.
 */
public class RedisOperationManager {


    private static RedisManager redisManager;
    private static RedisOperationManager redisOperationManager;

    public static RedisOperationManager init() {
        if (redisOperationManager == null) {
            redisOperationManager = new RedisOperationManager();
            redisManager = new RedisManager();
            redisManager.init();
        }

        return redisOperationManager;
    }

    public void setDate(String key, Object value) {
        redisManager.set(key.getBytes(), SerializeUtils.serialize(value));
    }

    public void setDate(String key, Object value, int expire) {
        redisManager.set(key.getBytes(), SerializeUtils.serialize(value), expire);
    }

    public String getString(String key) {
        byte[] bytes = redisManager.get(key.getBytes());
        String content = (String) SerializeUtils.deserialize(bytes);
        return content;
    }

    public int getInt(String key) {
        byte[] bytes = redisManager.get(key.getBytes());
        //如果redis中没有该key，则值为0
        int content = bytes == null ? 0 : (Integer) SerializeUtils.deserialize(bytes);
        return content;
    }


    public Object getObject(String key) {
        byte[] bytes = redisManager.get(key.getBytes());
        Object content = (Object) SerializeUtils.deserialize(bytes);
        return content;
    }

    public void delDate(Object key) {
        redisManager.del(SerializeUtils.serialize(key));
    }


}
