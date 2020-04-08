package com.mybatis.mybatis.cfg;

import com.mybatis.mybatis.mapper.SqlMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库连接的配置信息
 */
public class Configuration {
    private String driver;

    private String url;

    private String password;

    private String username;

    private Map<String, SqlMapper> sqlMappers = new HashMap<>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, SqlMapper> getMappers() {
        return sqlMappers;
    }

    public void setMappers(Map<String, SqlMapper> sqlMappers) {
        this.sqlMappers = sqlMappers;
    }
}
