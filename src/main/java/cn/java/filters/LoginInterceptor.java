package cn.java.filters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.java.entity.User;

@Controller
@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	Logger log = Logger.getLogger(LoginInterceptor.class);
	
	//这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
    	System.out.println(handler);
    	System.out.println(request.getSession().getId());
    	HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        User user = (User) session.getAttribute("session_user");
        //如果session中没有user，表示没登陆
        if (user == null){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
//        	log.info("尚未登录，跳转到登录界面");
//			response.sendRedirect(request.getContextPath()+"/pages/admin/login.jsp");
			response.sendRedirect(request.getContextPath()+"/admin/login");//拦截后跳转的方法
			log.info("已成功拦截并转发跳转");
            return false;
        }else {
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }
 

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
//	
//	/**
//	 * 是否进行登陆过滤
//	 * @param path
//	 * @param basePath
//	 * @return
//	 */
//	private boolean doLoginInterceptor(String path,String basePath){
//		path = path.substring(basePath.length());
//		Set<String> notLoginPaths = new HashSet<String>();
//		//设置不进行登录拦截的路径：登录注册和验证码
////		notLoginPaths.add("/pages/admin/login.jsp");
//		notLoginPaths.add("/admin/isLogin.do");
//		notLoginPaths.add("/admin/login.do");
//		//notLoginPaths.add("/loginTimeout");
//		
//		if(notLoginPaths.contains(path)) return false;
//		return true;
//	}
	
}
