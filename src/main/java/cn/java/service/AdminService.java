
package cn.java.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import cn.java.entity.Menu;

public interface AdminService {
	
	/**
	 * 判断是否登陆成功
	 * @param username
	 * @param password
	 * @return
	 */
    boolean isLogin(String username, String password);

    /**
     * 获取特定菜单项对应页面
     * @param id
     * @return
     */
    public List<Map<String, Object>> setectNavs(Long id);
    
    /**
     * 获取所有菜单项
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> setectMenu(int page, int rows);
    
    /**
     * 添加菜单
     * @param menu
     * @return
     */
    public String addMenu(Menu menu);
    
    /**
     * 修改菜单
     * @param menu
     * @return
     */
    public String editMenu(Menu menu);
    
    /**
     * 删除菜单
     * @param list
     * @return
     */
    public String removeMenu(List<Integer> list);
    
    public Map<String, Object> selectUser(int page, int rows);
}
