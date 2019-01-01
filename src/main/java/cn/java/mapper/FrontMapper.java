
package cn.java.mapper;

import org.apache.ibatis.annotations.Select;

public interface FrontMapper {

    /**
     * 
     * Description: 判断用户是否登录成功<br/>
     * @pram username
     * @param password
     * @return
     */
    @Select("SELECT COUNT(*) AS num FROM user WHERE username=#{arg0} AND PASSWORD=#{arg1}")
    int isUserExist(String username, String password);

}
