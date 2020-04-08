package com.mybatis.mybatis.io;

import java.io.InputStream;
/**
 * 根据输入流构建SqlSessionFactory对象
 */
public class Resources {
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
