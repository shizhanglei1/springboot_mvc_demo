package com.mybatis.mybatis.session;

import com.mybatis.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.mybatis.mybatis.util.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 根据输入流构建sqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        return new DefaultSqlSessionFactory(
                XMLConfigBuilder.loadConfiguration(inputStream)
        );
    }
}
