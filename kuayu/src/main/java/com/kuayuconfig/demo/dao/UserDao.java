package com.kuayuconfig.demo.dao;


import com.alibaba.fastjson.JSONObject;
import com.kuayuconfig.demo.bean.UserBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;



@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    JedisPool jedisPool;

    public int add(UserBean userBean){
        return jdbcTemplate.update("insert into users(id,name,age,email) value (?,?,?,?)",userBean.getId(),userBean.getName(),userBean.getAge(),userBean.getEmail());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from users  where id in(" + id + ")");
    }

    public int update(UserBean userBean) {
        return jdbcTemplate.update("update users set name=?,age=? ,email=? where id = ?",
                new Object[]{userBean.getName(),userBean.getAge(),userBean.getEmail(),userBean.getId()});
    }

    public UserBean queryById(int id){
        UserBean userBean = null;
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String result = jedis.get(String.valueOf(id));
            if(result != null && !"".equals(result)) {
                userBean = (UserBean) JSONObject.parseObject(result,UserBean.class);
                return userBean;
            }

            userBean = (UserBean)jdbcTemplate.queryForObject("select * from users where id in (" + id + ") ",new BeanPropertyRowMapper(UserBean.class));
            String userJson = JSONObject.toJSONString(userBean);
            jedis.set(String.valueOf(id),userJson);
        }finally {
            if(jedis != null)
                jedis.close();
        }

        return userBean;
    }

    public List<UserBean> query() {
        List<UserBean> list = jdbcTemplate.query("select * from users ",new BeanPropertyRowMapper(UserBean.class));
        return list;
    }




}
