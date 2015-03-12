package com.socialmap.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * Created by yy on 3/4/15.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    @PermitAll
    public String hello(){
        return "hello";
    }

    @RequestMapping("/a/{name}")
    public String a(@PathVariable String name){
        return "aaaa"+name;
    }

    @RequestMapping("/error")
    public String error(){
        return "Error";
    }

}
