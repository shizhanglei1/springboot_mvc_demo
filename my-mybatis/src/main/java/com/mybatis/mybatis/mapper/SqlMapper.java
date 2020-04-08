package com.mybatis.mybatis.mapper;
/**
 * 要查询的sql语句和返回类型的全限定名，封装dao包类的每一个接口的每一个方法
 */
public class SqlMapper {
    /**
     * 要查询的sql语句
     */
    private String queryString;
    /**
     * 查询后要封装的类型
     */
    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
