
package cn.java.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.service.FrontService;

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private FrontService frontService;

    @RequestMapping("/selectUser.do")
    @ResponseBody
    public int selectUser(String username, String password) {
        return frontService.getUser(username, password);
    }

    // ---------------------视图解析器-------------------------------------
    @RequestMapping("/testView.do")
    public String testView() {
        return "front/testView.jsp";
    }

}
