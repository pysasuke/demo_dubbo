package com.py.demo.controller;

import com.py.demo.enums.ResponseCode;
import com.py.demo.model.User;
import com.py.demo.service.UserServicre;
import com.py.demo.util.ResponseUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/20.
 */
@Controller
@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "user")
public class UserController {
    @Resource
    private UserServicre userServicre;

    @RequestMapping(value = "regist", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "regist", httpMethod = "POST", response = UserController.class, notes = "regist")
    public ResponseEntity regist(@ModelAttribute User user) {
        userServicre.insert(user);
        return ResponseUtil.genResponse(ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "login", httpMethod = "POST", response = UserController.class, notes = "login")
    public ResponseEntity login(@ModelAttribute User user) {
        userServicre.login(user);
        return ResponseUtil.genResponse(ResponseCode.SUCCESS);
    }



}
