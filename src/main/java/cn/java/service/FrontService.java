
package cn.java.service;

public interface FrontService {

    /**
     * 
     * Description: 判断用户是否登录成功<br/>
     * @param username
     * @param password
     * @return
     */
    int getUser(String username, String password);

}
