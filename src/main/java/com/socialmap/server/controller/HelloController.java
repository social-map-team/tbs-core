package com.socialmap.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * Created by yy on 3/4/15.
 */
@RestController
public class HelloController {

    @RequestMapping("/error/401")
    public String error401() {
        return "401 Unauthorized";
    }

    @RequestMapping("/error/404")
    public String error404() {
        return "404 Not Found";
    }

    @RequestMapping("/hello")
    @PermitAll
    public String hello() {
        return "hello";
    }

}
