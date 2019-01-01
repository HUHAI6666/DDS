
package cn.java.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 
     * Description: 后台登录方法<br/>
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/isLogin.do")
    @ResponseBody
//    public boolean isLogin(String username, String password) {
//        System.out.println(username);
//        return adminService.isLogin(username, password);
//    }
    //配套序列化的params
    public boolean isLogin(@RequestBody Map<String, Object> params) {
        System.out.println(params.get("username"));
        
        return adminService.isLogin(params.get("username").toString(), params.get("password").toString());
    }
    
    @RequestMapping(value = "/getAllNavs.do")
    @ResponseBody
    public List<Map<String, Object>> getAllNavs(@RequestParam(defaultValue = "0") Long id) {
		return adminService.setectNavs(id);
	}
    
    
    @RequestMapping(value = "/getAllMenu.do")
    @ResponseBody
    public Map<String, Object> getAllMenu(int page, int rows) {
		return adminService.setectMenu(page, rows);
	}
    
}
