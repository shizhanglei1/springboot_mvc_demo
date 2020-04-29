package com.zjg.tomdog.annotations;

import java.lang.annotation.*;

/**
 * 注解的类定义
 */
@Documented  //生成javadoc文档
@Target(ElementType.TYPE)  //表示注解的范围，接口、类、枚举和注解
@Retention(RetentionPolicy.RUNTIME) //注解生命周期，一直保留
public @interface WebService {

}
