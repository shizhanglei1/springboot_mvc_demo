package spring.mvc.demo.controller;

/**
 * @ControllerAdvice（看成spring mvc提供的一个特殊的拦截器）。
 * @ControllerAdvice是一个@Component，用于定义@ExceptionHandler（最主要用途），
 * @InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法（拦截）。
 *
 * 引申：@interface 元注解
 * @Target(ElementType.TYPE) ：该注解应用到什么地方。
 * @Retention(RetentionPolicy.RUNTIME)：什么时候应用。
 *
 * @ExceptionHandler：为所有controller封装统一异常处理代码。
 * @ModelAttribute：为所有controller设置全局变量。
 * @InitBinder：用于为所有controller设置某个类型的数据转换器。
 */

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动应用后，被 @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，
 * 都会作用在 被 @RequestMapping 注解的方法上
 */
@ControllerAdvice
public class CatchExceptionTest {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex){
        Map map = new HashMap();
        map.put("code","0000");
        map.put("mesg",ex.getMessage());
        return map;
    }

    /**
     * 把值绑定到Model中，使用全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        System.out.println("添加全局变量");
        model.addAttribute("username","jack");
    }

}
