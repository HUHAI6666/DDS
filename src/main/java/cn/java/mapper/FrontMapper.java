
package cn.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.Account;
import cn.java.entity.Test;

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
    
    @Select("SELECT * FROM account LIMIT #{arg0},#{arg1}")
    List<Account> getAllData(int page, int rows);
    
    @Select("SELECT count(*) as total FROM account")
    int count();

}
