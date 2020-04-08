package com.mybatis.mybatis.session;

/**
 * 创建SqlSession对象的工厂类
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
