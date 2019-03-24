
package cn.java.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.java.entity.Rule;
import cn.java.entity.Test;

public interface FrontService {

    /**
     * 
     * Description: 判断用户是否登录成功<br/>
     * @param username
     * @param password
     * @return
     */
    int getUser(String username, String password);
    
    /**
     * 获取所有加密前的数据（分页）
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> getAllData(int page, int rows);
    
    /**
     * 获取所有加密后的数据（分页）
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> getAllEncryptData(int page, int rows);

    /**
     * 文件上传
     * @param file
     * @param request
     * @param response
     * @return
     */
    String fileUpload(MultipartFile file,HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 配置脱敏规则
     * @param rule
     * @return
     */
    String insertRule(Rule rule);
    
    /**
     * 初始化脱敏规则
     * @return
     */
    Map<String, Object> initRule();
}
