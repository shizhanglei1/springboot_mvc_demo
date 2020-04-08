package com.mybatis.mybatis.proxy;

import com.mybatis.mybatis.mapper.SqlMapper;
import com.mybatis.mybatis.util.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;


/**
 * 增强Mapper，生成代理对象
 */
public class MapperProxy implements InvocationHandler {

    private Map<String, SqlMapper> sqlMappers;

    private Connection connection;

    public MapperProxy(Map<String, SqlMapper> sqlMappers, Connection connection) {
        this.sqlMappers = sqlMappers;
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法名和全限名，根据方法名得到Mapper对象
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        SqlMapper sqlMapper = sqlMappers.get(className+"."+methodName);
        return new Executor().selectList(sqlMapper,connection);
    }
}
