
package cn.java.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.java.entity.Test;
import cn.java.mapper.FrontMapper;
import cn.java.mapper.TestMapper;
import cn.java.service.FrontService;
import cn.java.utils.FileUpload;

@Service
public class FrontServiceImpl implements FrontService {
    @Autowired
    private FrontMapper frontMapper;
    @Autowired
    private TestMapper testMapper;
//    @Autowired
//    private FileUpload fileUpload;
    private static Logger logger = Logger.getLogger(FrontServiceImpl.class);

    private static final long serialVersionUID = 1302377908285976972L;

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see cn.java.service.impl.FrontService#getUser(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public int getUser(String username, String password) {
        return frontMapper.isUserExist(username, password);
    }
    
    @Override
    public Map<String, Object> getAllData(int page, int rows){
    	int startIndex = (page-1)*rows;
    	List<Test> list = frontMapper.getAllData(startIndex, rows);
    	int total = frontMapper.count();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("rows", list);
    	map.put("total", total);
    	return map;
    }
    
    @Override
    public String fileUpload(MultipartFile file,HttpServletRequest request, HttpServletResponse response){
    	
    	String fileName = file.getOriginalFilename();
    	String filePath = "E:\\fileUpload\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
//            request.getRequestDispatcher("/pages/admin/fail.jsp").forward(request, response);
        } catch (IOException e) {
        	logger.error(e.toString(), e);
        	return "上传失败";
        } 
    	
//    	String path = "src/main/resources/testdata.txt";
    	FileUpload fileUpload = new FileUpload();
    	List<Test> list = fileUpload.listToObject(fileUpload.readFile(filePath + fileName));
    	testMapper.insert(list);
    	return "上传成功";
    }
}
