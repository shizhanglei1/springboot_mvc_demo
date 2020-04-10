package com.cachingehcache.demo.controller;

import com.cachingehcache.demo.bean.UserBean;
import com.cachingehcache.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CacheConfig(cacheNames = "codeCache")
@Controller
@RequestMapping
public class WebController {

    @Autowired
    UserDao userDao ;
    @ResponseBody
    @RequestMapping("/query")
    @Cacheable
    public List<UserBean> query(){
        System.out.println("没有去缓存得地方");
        List<UserBean> list = userDao.query();

        return list;
    }


    @ResponseBody
    @GetMapping(value = "/add")
    //required=false 表示url中可以不穿入id参数，此时就使用默认参数
    public int sayHello(){
        UserBean userBean = new UserBean(6,"szl",25,"test@qq.com");
        return userDao.add(userBean);
    }
}
