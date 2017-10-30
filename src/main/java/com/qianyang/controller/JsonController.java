package com.qianyang.controller;

import com.qianyang.common.annotations.ResponseMapping;
import com.qianyang.common.domain.JsonResultModel;
import com.qianyang.common.enums.ResponseType;
import com.qianyang.common.enums.ResultStatusCode;
import com.qianyang.common.exception.UnknownResourceException;
import com.qianyang.common.model.User;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * http://localhost:8080/xManager/json/testJson
 * 仅仅用于测试
 */
@Controller
@RequestMapping("/json")
public class JsonController {
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

    @ResponseBody
    @RequestMapping("/testJson")
    public JsonResultModel<User> testJson(){
        Map<String, User> users = new HashMap<String, User>();
        users.put("u1", new User("wangyuan", 2));
        users.put("u2", new User());
        users.put("u3", null);

        return new JsonResultModel(users).code(ResultStatusCode.SUCCESS);

        //"code":"SUCCESS","data":{"u1":{"name":"wangyuan","age":2},"u2":{},"u3":""}}
        //可以看出虽然设置了 不序列化为null的字段，但是map里的null值并不受影响，照样输出""
    }

    @RequestMapping("/error")
    @ResponseMapping(type= ResponseType.PAGE)
    public String error(){
        throw new UnknownResourceException("------------");
        //return "views/index";
    }

    @RequestMapping("/error1")
    @ResponseMapping(type= ResponseType.PAGE, page="views/error1")
    public String error1(){
        throw new UnknownResourceException("------------");
        //return "views/index";
    }
}
