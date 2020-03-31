package com.pringbootlistenerservletfilterinterceptor.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置 IndexController，用于测试 /index/IndexController 路径是否会被 Filter 过滤和 Interceptor 拦截，
 * 并验证两者的先后顺序。
 */
@RestController
@RequestMapping("index")
public class IndexController {
    @GetMapping("IndexController")
    public String index() throws Exception {
        return "IndexController";
    }
}


