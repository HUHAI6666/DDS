
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

import cn.java.entity.Account;
import cn.java.entity.Rule;
import cn.java.entity.Test;
import cn.java.mapper.AccountMapper;
import cn.java.mapper.FrontMapper;
import cn.java.mapper.RuleMapper;
import cn.java.mapper.TestMapper;
import cn.java.service.FrontService;
import cn.java.utils.FileUpload;
import cn.java.utils.RandomName;

@Service
public class FrontServiceImpl implements FrontService {
    @Autowired
    private FrontMapper frontMapper;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private AccountMapper accountMapper;
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
    	List<Account> list = frontMapper.getAllData(startIndex, rows);
    	int total = frontMapper.count();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("rows", list);
    	map.put("total", total);
    	return map;
    }
    
    @Override
    public Map<String, Object> getAllEncryptData(int page, int rows){
    	int startIndex = (page-1)*rows;
    	List<Account> list = frontMapper.getAllData(startIndex, rows);
    	List<Account> encryptList = encryptData(list);
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
    	File directory = new File(filePath);
    	if(!directory.isDirectory()){
    		directory.mkdir();
    	}
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
//    	List<Test> list = fileUpload.listToObject(fileUpload.readFile(filePath + fileName));
    	List<Account> list = fileUpload.listToObject(fileUpload.readFile(filePath + fileName));
//    	testMapper.insert(list);
    	accountMapper.insert(list);
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
    
    public List<Account> encryptData(List<Account> list){
    	Rule rule = ruleMapper.getRule();
    	if(rule == null){
    		return list;
    	}
    	//脱敏
    	for(Account account : list){
    		account.setName(encryptName(account.getName(), rule));
    		account.setEmail(encryptEmail(account.getEmail(), rule));
    		account.setIdNo(encryptIdNo(account.getIdNo(), rule));
    		account.setPhone(encryptPhone(account.getPhone(), rule));
    		account.setNickName(encryptNickName(account.getNickName(), rule));
    		account.setUserName(encryptUserName(account.getUserName(),rule));
    		account.setPassword(encryptPassword(account.getPassword(),rule));
    	}
		return list;
    }
    
    public String encryptName(String name, Rule rule){
    	if(("11").equals(rule.getName())){
    		name = name.substring(0, 1) + "**";
    	}
    	if(("12").equals(rule.getName())){
    		if(name.length()>2){
    			Random random = new Random();
    			int type = random.nextInt(2);
    			if(type ==1){   //单姓
    				name = RandomName.randomName(true, name.length());
    			}
    			else{   //复姓
    				name = RandomName.randomName(false, name.length());
    			}
    		}
    		else{
    			name = RandomName.randomName(true, name.length());
    		}
    		
    	}
		return name;
    }
    
    public String encryptIdNo(String idNo, Rule rule){
    	if(idNo.length() != 18){
    		return idNo;
    	}
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
    
    //4--
    public String encryptNickName(String nickName, Rule rule){
    	int len = nickName.length();
    	if("41".equals(rule.getNickName())){
    		String patten = createPatten(len - 2);
    		nickName = nickName.substring(0, 1) + patten + nickName.substring(len - 1);
    	}
    	if("42".equals(rule.getNickName())){
    		char[] temp = nickName.toCharArray();
    		Random random = new Random();
    		for(int i = 0 ; i < len ; i ++){
    			
    			//如果该字符是数字
    			if(Character.isDigit(temp[i])){
    				temp[i] = (char)(Math.random()*26+'a');
//    				temp[i] = (char)(random.nextInt(26)+'a');
    			}
    		}
    		nickName = String.valueOf(temp);
    	}
    	return nickName;
    }
    
    //6--
    public String encryptUserName(String userName, Rule rule){
    	if("61".equals(rule.getUserName())){
    		userName = userName.substring(0, 3) + createPatten(7);
    	}
    	if("62".equals(rule.getUserName())){
    		int len = userName.length();
    		int mid = len / 2;
    		Random random = new Random();
    		int start = random.nextInt(mid);
    		int tmp = random.nextInt(len);
    		int end = tmp > mid ? tmp - 1 : tmp + mid - 1;
    		userName = userName.substring(start, end);
    	}
    	return userName;
    }

    //7--
    public String encryptPassword(String password, Rule rule){
    	if("71".equals(rule.getPassword())){
    		password = password.replaceAll("[a-zA-Z]+", "");
    	}
    	if("72".equals(rule.getPassword())){
    		password = password.replaceAll("\\d+", "");
    	}
    	return password;
    }
    
    //生成长度为len个*的填充字符串
    private String createPatten(int len){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < len ; i ++){
    		sb.append("*");
    	}
    	return sb.toString();
    }
}
