package com.qianyang.controller;

import com.qianyang.common.domain.JsonResultModel;
import com.qianyang.common.enums.ResultStatusCode;
import com.qianyang.model.User;
import com.qianyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public JsonResultModel save(@RequestBody User user){

        user.setId(UUID.randomUUID().toString().substring(0, 32));

        userService.save(user);

        return new JsonResultModel().code(ResultStatusCode.SUCCESS);
    }
}
