/**
 * Project Name:springboot2
 * File Name:AdminServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午5:29:09
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.AdminMapper;
import cn.java.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see cn.java.service.impl.AdminService#isLogin(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean isLogin(String username, String password) {
        int result = adminMapper.isLogin(username, password);
        if (result >= 1) {
            return true;
        }
        return false;
    }
    @Override
    public List<Map<String, Object>> setectNavs(Long id){
    	return adminMapper.setectNavs(id);
    }
    
    @Override
    public Map<String, Object> setectMenu(int page, int rows){
    	int startIndex = (page-1)*rows;
    	List<Map<String, Object>> listMaps = adminMapper.setectMenu(startIndex, rows);
    	int total = adminMapper.countNavs();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("rows", listMaps);
    	map.put("total", total);
    	return map;
    }
}
