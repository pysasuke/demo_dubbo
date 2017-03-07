package com.py.demo.service;

import com.py.demo.enums.ResponseCode;
import com.py.demo.dao.UserMapper;
import com.py.demo.model.User;
import com.py.demo.util.CommonUtil;
import com.py.demo.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service("userServiceImpl")
public class UserServicreImpl implements UserServicre{

    @Autowired
    private UserMapper userMapper;

    public int insert(User user) {

        //MD5加密
        user.setPassword(CommonUtil.EncoderByMd5(user.getPassword()));

        return userMapper.insert(user);
    }

    public void login(User user) {

        User thisUser = userMapper.getByUsername(user.getUsername());
        boolean isOk = checkPassword(user.getPassword(), thisUser.getPassword());
        if (!isOk) {
            throw new ServiceException(ResponseCode.ILLEGAL_REQUEST);
        }

    }



    public boolean checkPassword(String loginPwd, String dbPwd) {
        if (CommonUtil.EncoderByMd5(loginPwd).equals(dbPwd)) {
            return true;
        } else {
            return false;
        }
    }

}
