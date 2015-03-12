package com.socialmap.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by yy on 3/4/15.
 */
@ControllerAdvice
@ResponseBody
public class ErrorController {
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notExistedURI(){
        return "Not existed URI";
    }
}
