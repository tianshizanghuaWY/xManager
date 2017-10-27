package com.qianyang.controller;

import com.qianyang.common.domain.JsonResultModel;
import com.qianyang.common.enums.ResultStatusCode;
import com.qianyang.common.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping("/test")
    public JsonResultModel<User> test(){
        return new JsonResultModel<User>(new User("wangyuan", 1))
                .code(ResultStatusCode.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/testy")
    public User testy(){
        return new User("wangyuan", 1);
    }

    @RequestMapping("/page")
    public String page(){
        return "views/index";
    }
}
