package com.qianyang.web.controller;

import com.qianyang.common.http.exception.UnknownResourceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 响应无效的URL
 */
@Controller
public class DefaultController {

    @RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        throw new UnknownResourceException("There is no resource for path " + uri);
    }
}
