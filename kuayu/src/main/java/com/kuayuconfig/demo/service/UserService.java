package com.kuayuconfig.demo.service;

import com.kuayuconfig.demo.bean.UserBean;
import com.kuayuconfig.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public int add(UserBean userBean){
        return userDao.add(userBean);
    }

    public int deleteById(int id){
        return userDao.deleteById(id);
    }

    public int update(UserBean userBean){
        return userDao.update(userBean);
    }

    public List<UserBean> query() {
        return userDao.query();
    }

    public UserBean queryById(int id) {
        return userDao.queryById(id);
    }
}
