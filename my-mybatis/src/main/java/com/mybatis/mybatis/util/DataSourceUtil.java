package com.mybatis.mybatis.util;

import com.mybatis.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
public class DataSourceUtil {
    /**
     * 通过配置获取一个连接
     * @param cfg 配置类
     * @return 根据配置类读取的配置信息创建的连接
     */
    public static Connection getConnection(Configuration cfg){
        try{
            Class.forName(cfg.getDriver());
            return  DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
