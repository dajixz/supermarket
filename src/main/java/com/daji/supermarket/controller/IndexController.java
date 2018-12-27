package com.daji.supermarket.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 大稽
 * @date2018/12/1016:22
 */
public class IndexController {

    @GetMapping("/")
    public String indexView(){
        return "index";
    }
}
