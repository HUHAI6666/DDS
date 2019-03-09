
package cn.java.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    
    Map<String, Object> getAllData(int page, int rows);

    String fileUpload(MultipartFile file,HttpServletRequest request, HttpServletResponse response);
}
