
package cn.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.FrontMapper;
import cn.java.service.FrontService;

@Service
public class FrontServiceImpl implements FrontService {
    @Autowired
    private FrontMapper frontMapper;

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
}
