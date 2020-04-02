package com.kuayuconfig.demo.controller;

import com.kuayuconfig.demo.bean.UserBean;
import com.kuayuconfig.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class WebController {

    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping("/query")
    public List<UserBean> query(){
        List<UserBean> list = userService.query();

        return list;
    }

    @ResponseBody
    @RequestMapping("/queryId")
    public UserBean queryById(@RequestParam(value="id",required = false,defaultValue = "1") int id){
        UserBean list = userService.queryById(id);

        return list;
    }

    @ResponseBody
    @GetMapping(value = "/add")
    //required=false 表示url中可以不穿入id参数，此时就使用默认参数
    public int sayHello(){
        UserBean userBean = new UserBean(6,"szl",25,"test@qq.com");
        return userService.add(userBean);
    }
}
