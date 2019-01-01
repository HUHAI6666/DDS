
package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

/**
 * Description: easyui后台 <br/>
 */
public interface AdminMapper {

    /**
     * 
     * Description: 后台登录<br/>
     *
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT COUNT(*) AS num FROM user WHERE username=#{arg0} AND PASSWORD=#{arg1}")
    int isLogin(String username, String password);
    
    /**
     * 查询后台导航栏
     * @return
     */
    @Select("SELECT * FROM admin_nav WHERE pid = #{arg0}")
    List<Map<String, Object>> setectNavs(Long id);
    
    @Select("SELECT * FROM admin_nav LIMIT #{arg0},#{arg1}")
    List<Map<String, Object>> setectMenu(int startIndex, int rows);
    
    /**
     * 查询所有记录条数
     * @return
     */
    
    @Select("SELECT count(*) as total FROM admin_nav")
    int countNavs();
}
