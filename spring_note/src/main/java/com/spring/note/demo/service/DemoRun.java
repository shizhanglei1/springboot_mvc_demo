package com.spring.note.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Configuration  //想让Spring的配置文件生效，加载进来，@ImportResource标注在一个配置类上。
@ImportResource(locations = {"classpath:bean.xml"})
public class DemoRun {

    @Autowired
    TestService testService;

    @RequestMapping("/abc")
    @ResponseBody
    public String test() {
        testService.test();
        return "1";
    }

}
