
package cn.java.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.java.entity.Rule;
import cn.java.entity.Test;
import cn.java.mapper.FrontMapper;
import cn.java.mapper.RuleMapper;
import cn.java.mapper.TestMapper;
import cn.java.service.FrontService;
import cn.java.utils.FileUpload;

@Service
public class FrontServiceImpl implements FrontService {
    @Autowired
    private FrontMapper frontMapper;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RuleMapper ruleMapper;
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
    public Map<String, Object> getAllEncryptData(int page, int rows){
    	int startIndex = (page-1)*rows;
    	List<Test> list = frontMapper.getAllData(startIndex, rows);
    	List<Test> encryptList = encryptData(list);
    	int total = frontMapper.count();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("rows", encryptList);
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
    
    @Override
    public String insertRule(Rule rule){
    	int key = ruleMapper.insert(rule);
    	System.out.println(rule.getId());
    	System.out.println(key);
    	if(key ==1){
    		return "success";
    	}
    	return "false";
    }
    
    @Override
    public Map<String, Object> initRule(){
    	Map<String,  Object> resultMap = new HashMap<String, Object>();
    	Rule rule = ruleMapper.getRule();
    	System.out.println(rule);
    	boolean result = false;
    	if(rule != null){
    		result = true;
    	}
    	resultMap.put("result", result);
    	resultMap.put("data", rule);
    	return resultMap;
    }
    
    public List<Test> encryptData(List<Test> list){
    	Rule rule = ruleMapper.getRule();
    	if(rule == null){
    		return list;
    	}
    	for(Test test : list){
    		test.setMn(encryptName(test.getMn(), rule));
    		test.setId(encryptIdNo(test.getId(), rule));
    	}
		return list;
    }
    
    public String encryptName(String name, Rule rule){
    	if(("11").equals(rule.getName())){
    		name = name.substring(0, 1) + "**";
    	}
    	if(("12").equals(rule.getName())){
//    		name = name.substring(0, 1) + "**";
    	}
		return name;
    }
    
    public String encryptIdNo(String idNo, Rule rule){
    	if("21".equals(rule.getIdNo())){
    		idNo = "**************" + idNo.substring(14);
    	}
    	if("22".equals(rule.getIdNo())){
    		idNo = idNo.substring(0, 6) + "********" + idNo.substring(14);
    	}
		return idNo;
    }
    
    public String encryptPhone(String phone, Rule rule){
    	if("31".equals(rule.getPhone())){
    		phone = phone.substring(0, 3) + "****" + phone.substring(7);
    	}
    	if("32".equals(rule.getPhone())){
    		phone = phone.substring(0, 3) + "****0000" ;
    	}
		return phone;
    }
    
    public String encryptAddress(String address, Rule rule){
    	if("41".equals(rule.getAddress())){
    		int i = address.indexOf("区");
    		if(i != -1){
    			address = address.substring(0, i + 1) + "******";
    		}
    	}
    	if("42".equals(rule.getAddress())){
    		int i = address.indexOf("市");
    		if(i != -1){
    			address = address.substring(0, i + 1);
    		}
    	}
		return address;
    	
    }
    
    public String encryptEmail(String email, Rule rule){
    	if("51".equals(rule.getEmail())){
    		int index = email.lastIndexOf("@");
    		email = email.substring(0, 1) + "***" + email.substring(index);
    	}
    	if("52".equals(rule.getEmail())){
    		int index = email.lastIndexOf("@");
    		int index1 = email.lastIndexOf(".");
    		email = email.substring(0, index + 1) + "***" + email.substring(index1);
    	}
		return email;
    	
    }
    
    public String encryptCardNo(String cardNo, Rule rule){
    	if("61".equals(rule.getCardNo())){
    		cardNo = cardNo.substring(0, 6) + "******" + cardNo.substring(12);
    	}
    	if("62".equals(rule.getCardNo())){
    		cardNo = cardNo.substring(0, 6);
    		Random random = new Random();
    		for(int i = 0 ; i < 10 ; i++){
    			int key = random.nextInt();
    			cardNo += key + "";
    		}
    	}
		return cardNo;
    	
    }
}
