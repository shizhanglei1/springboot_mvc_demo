package spring.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/web")
public class WebController {

    @RequestMapping(value = "index")
    //ModelMap讲解如下
    public String index(ModelMap map){
        map.addAttribute("title","freemarker hello word");
        return "freemarker/test1";
    }
}

/**
 *Spring中Model、ModelMap及ModelAndView之间的区别
 1. Model（org.springframework.ui.Model）
        Model是一个接口，包含addAttribute方法，其实现类是ExtendedModelMap。
        ExtendedModelMap继承了ModelMap类，ModelMap类实现了Map接口。
 2. ModelMap（org.springframework.ui.ModelMap）
        Spring框架自动创建modelmap的实例，并作为controller方法的参数传入，用户无需自己创建对象。
        ModelMap对象主要用于把controller方法处理的数据传递到jsp界面，在controller方法中把jsp界面需要的数据放到
        ModelMap对象中即可。它的作用类似request对象的setAttribute方法。通过以下方法向页面传递参数：
 3. ModelAndView（org.springframework.web.servlet.ModelAndView）
        ModelAndView对象有两个作用：
           1) 设置url地址（这也是ModelAndView和ModelMap的主要区别）。
           2) 把controller方法中处理的数据传到jsp页面，在controller方法中把jsp界面需要的数据放到ModelAndView对
           象中即可。然后return mv。它的作用类似request对象的setAttribute方法。通过以下方法向页面传递参数：
 */