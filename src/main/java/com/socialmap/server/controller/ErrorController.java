package com.socialmap.server.controller;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by yy on 3/4/15.
 */
@ControllerAdvice
@ResponseBody
public class ErrorController {
    @ExceptionHandler(Throwable.class)
    public void error(Throwable e) {
        e.printStackTrace();
    }

    @ExceptionHandler(HibernateException.class)
    public void hibernateException(HibernateException e) {
        e.printStackTrace();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notExistedURI() {
        return "Not existed URI";
    }
}
