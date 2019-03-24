
package cn.java.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.java.entity.User;
import cn.java.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public ModelAndView toIndex() {// 配置默认访问首页
    	return new ModelAndView("login");
    }
    
    /**
     * 
     * Description: 后台登录方法<br/>
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/isLogin")
    @ResponseBody
//    public boolean isLogin(String username, String password) {
//        System.out.println(username);
//        return adminService.isLogin(username, password);
//    }
    //配套序列化的params
    public boolean isLogin(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        System.out.println("welcome " + params.get("name"));
        if(adminService.isLogin(params.get("name").toString(), params.get("password").toString())){
        	User user = new User();
        	user.setName(params.get("name").toString());
        	user.setPassword(params.get("password").toString());
        	request.getSession().setAttribute("session_user",user);
        	return true;
        }
        return false;
    }
    
    //退出
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //移除session
        request.getSession().invalidate();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
        return "redirect:" + basePath;
    }

    
    @RequestMapping(value = "/getAllNavs")
    @ResponseBody
    public List<Map<String, Object>> getAllNavs(@RequestParam(defaultValue = "0") Long id) {
		return adminService.setectNavs(id);
	}
    
    
    @RequestMapping(value = "/getAllMenu")
    @ResponseBody
    public Map<String, Object> getAllMenu(int page, int rows) {
		return adminService.setectMenu(page, rows);
	}
    
}
