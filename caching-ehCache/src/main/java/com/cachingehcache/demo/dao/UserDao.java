package com.cachingehcache.demo.dao;
import com.cachingehcache.demo.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


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



    public List<UserBean> query() {
        List<UserBean> list = jdbcTemplate.query("select * from users ",new BeanPropertyRowMapper(UserBean.class));
        return list;
    }




}
