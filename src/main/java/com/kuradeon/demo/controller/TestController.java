package com.kuradeon.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test controller
 * @author kuradeon
 * @date 2019-01-30 16:30
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1/{str}")
    public String test1(@PathVariable String str) {
//        throw new RuntimeException("错了");
        return str;
    }
}
