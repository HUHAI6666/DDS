
package cn.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void test(){
    	String path = "src/main/resources/testdata.txt";
    	FileUpload fileUpload = new FileUpload();
    	List<Test> list = fileUpload.listToObject(fileUpload.readFile(path));
    	testMapper.insert(list);
    }
}
