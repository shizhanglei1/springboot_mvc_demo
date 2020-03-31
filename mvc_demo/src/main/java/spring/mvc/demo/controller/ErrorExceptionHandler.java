package spring.mvc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理方法2：
 *     使用注解@ControllerAdvice（这是一个拦截器，主要拦截项目中的异常，详细文档请参考CatchExceptionTest）
 */
/**
 * 异常详解：
 *  Exception包含运行时异常（RuntimeException，也叫非检查异常）和检查异常（也叫非运行时异常）两类。运行时
 *  异常是代码运行过程中发生的异常，比如NullPointerException等；检查运行是代码编写好后，在编译阶段语法不
 *  通过而需要处理的异常，是不运行就需要处理的异常。
 */


//@ControllerAdvice
public class ErrorExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);
    /**
     * 统一异常处理
     * @param exception
     *          excepion
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(RuntimeException exception){
        logger.info("自定义异常处理-RuntimeException");
        ModelAndView mav = new ModelAndView();
        mav.addObject("roncooException",exception.getMessage());
        return mav;
    }

    /**
     * 统一异常处理
     * @param exception
     *          excepion
     * @return
     */
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(Exception exception) {
        logger.info("自定义异常处理-Exception");
        ModelAndView m = new ModelAndView();
        m.addObject("roncooException", exception.getMessage());
        m.setViewName("error/500");
        return m;
    }

}
