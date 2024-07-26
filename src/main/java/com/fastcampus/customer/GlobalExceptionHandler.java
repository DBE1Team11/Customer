package com.fastcampus.customer;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice("com.fastcampus.customer")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String catcher1(Exception ex, Model m) {
//        m.addAttribute("ex",ex);
        m.addAttribute("msg", "Exception");
        return "errorPage";
    }

    @ExceptionHandler(NullPointerException.class)
    public String catcher2(Exception ex, Model m) {
//        m.addAttribute("ex",ex);
        m.addAttribute("msg", "NullException");
        return "errorPage";
    }
}
