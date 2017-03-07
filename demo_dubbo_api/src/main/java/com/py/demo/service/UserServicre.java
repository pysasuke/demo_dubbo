package com.py.demo.service;

import com.py.demo.model.User;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserServicre {

    int insert(User user);

    void login(User user);

    boolean checkPassword(String loginPwd, String dbPwd);


}
