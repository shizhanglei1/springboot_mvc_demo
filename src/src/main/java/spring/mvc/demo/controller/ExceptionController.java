package spring.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/get")
public class ExceptionController {

    @RequestMapping("/er1")
    public String exception1(){
        return "拦截器测试1";
    }
    @RequestMapping("/er2")
    public String exception2(){
        int i = 1/0;
        return "拦截器测试2";
    }
    @RequestMapping("mt")
    public Object modelTest(ModelMap modelMap){
        Object object = modelMap.get("username");
        return object;
    }
}
