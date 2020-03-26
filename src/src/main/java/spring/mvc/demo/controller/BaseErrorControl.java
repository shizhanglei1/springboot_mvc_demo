package spring.mvc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//异常处理方法一
/**
 * spring boot将所有的错误默认映射到/error，实现ErrorControl即可使用
 */
@Controller
//@RequestMapping(value = "/error")
public class BaseErrorControl implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(BaseErrorControl.class);

    //覆写父类的的方法，返回模版引擎页面
    @Override
    public String getErrorPath() {
        logger.info("出错了，进入自动用错误控制器");
        return "error/error";
    }
    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
