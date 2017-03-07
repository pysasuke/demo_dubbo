package com.py.demo.dao;

import com.py.demo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserMapper {
    public int insert(User user);

    public User getById(@Param("id") Long id);

    public User getByUsername(@Param("username") String username);
}
