package com.mybatis.mybatis.session.defaults;

import com.mybatis.mybatis.cfg.Configuration;
import com.mybatis.mybatis.session.SqlSession;
import com.mybatis.mybatis.session.SqlSessionFactory;
import com.mybatis.mybatis.util.DataSourceUtil;


/**
 * 根据传入的Configuration 创建sqlSession
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg.getMappers(), DataSourceUtil.getConnection(cfg));
    }

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }
}
