package com.mybatis.mybatis.session.defaults;

import com.mybatis.mybatis.mapper.SqlMapper;
import com.mybatis.mybatis.proxy.MapperProxy;
import com.mybatis.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;

/**
 * 获取Mapper代理对象执行查询
 */
public class DefaultSqlSession implements SqlSession {
    private Map<String, SqlMapper> sqlMapperMap;
    private Connection connection;

    public DefaultSqlSession(Map<String, SqlMapper> sqlMapperMap, Connection connection) {
        this.sqlMapperMap = sqlMapperMap;
        this.connection = connection;
    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(),new Class[]{mapperClass},new MapperProxy(sqlMapperMap,connection));
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
