package com.py.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/1/5.
 */
@Controller
@RequestMapping(value = "/freemarkerTest", produces = MediaType.APPLICATION_JSON_VALUE)
public class FreemarkerTest {
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String getFirstPage(Model model) {
        model.addAttribute("test", "my name is freemarker");
        return "hello.ftl";
    }
}
