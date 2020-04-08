package com.mybatis.mybatis.session;
/**
 * 自定义Mybatis中和数据库交互的核心类,可以创建dao包中Mapper接口的代理对象
 */
public interface SqlSession {
    /**
     * 通过mapper字节码获取代理对象
     *
     * @param mapperClass 被代理对象字节码对象
     * @param <T>         被代理类型
     * @return 代理对象
     */
    <T> T getMapper(Class<T> mapperClass);

    /**
     * 释放资源
     */
    void close();

}
