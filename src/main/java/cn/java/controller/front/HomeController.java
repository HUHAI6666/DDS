
package cn.java.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String toIndex() {// 配置默认访问首页
        return "redirect:/index.jsp";
    }

}
