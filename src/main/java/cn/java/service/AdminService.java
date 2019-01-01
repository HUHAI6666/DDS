
package cn.java.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

    boolean isLogin(String username, String password);

    public List<Map<String, Object>> setectNavs(Long id);
    
    public Map<String, Object> setectMenu(int page, int rows);
}
