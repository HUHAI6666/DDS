
package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.Menu;

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
    
    @Insert("insert into admin_nav(text,state,iconCls,pid,href) values(#{text},#{state},#{iconCls},#{pid},#{href})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertMenu(Menu menu);
    
    @Update("update admin_nav set text=#{text},state=#{state},iconCls=#{iconCls},pid=#{pid},href=#{href} where id=#{id}")
    int updateMenu(Menu menu);
    
    @Delete({"<script> delete from admin_nav where id in " +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\",\" close=\")\"> "+
            " #{item} " +
            "</foreach> </script>"})
    //@Delete("delete from admin_nav where id in #{list}")
    int deleteMenu(List<Integer> list);
    
    @Select("SELECT * FROM user LIMIT #{arg0},#{arg1}")
    List<Map<String, Object>> selectUser(int startIndex, int rows);
    
    @Select("SELECT count(*) as total FROM user")
    int countUsers();
}

