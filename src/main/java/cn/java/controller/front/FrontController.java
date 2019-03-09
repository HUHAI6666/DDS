
package cn.java.controller.front;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.java.entity.Test;
import cn.java.service.FrontService;

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private FrontService frontService;
    
    @RequestMapping(value = "/upload")
    public ModelAndView toIndex() {// 配置默认访问首页
//        return "redirect:/index.jsp";
    	return new ModelAndView("fileUpload");
    }

    @RequestMapping("/selectUser")
    @ResponseBody
    public int selectUser(String username, String password) {
        return frontService.getUser(username, password);
    }
    
    @RequestMapping("/getAllData")
    @ResponseBody
    public Map<String, Object> getAllData(int page, int rows){
    	return frontService.getAllData(page,rows);
    }
    
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String, Object> test(@RequestParam("file")MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
    	String meString = frontService.fileUpload(file,request,response);
    	request.getSession().setAttribute("message", meString);
    	Map<String,  Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("result", meString);
//    	if(meString == "上传成功"){
//    		return true;
//    	}
//    	try {
//			response.sendRedirect("/pages/admin/main.jsp");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	return resultMap;
    }

    // ---------------------视图解析器-------------------------------------
    @RequestMapping("/testView")
    public String testView() {
        return "testView";
    }

}
