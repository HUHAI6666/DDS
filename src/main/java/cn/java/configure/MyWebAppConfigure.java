package cn.java.configure;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.java.filters.LoginInterceptor;

@EnableWebMvc
@Configuration
public class MyWebAppConfigure extends WebMvcConfigurerAdapter {

	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("20MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
	
    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }
    
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/admin/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    
    /*资源处理器*/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//       registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/"+"/img/");
       registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    
    final String[] notLoginInterceptPaths = {"/","/admin/login","/admin/isLogin","/static/**"};
 // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(notLoginInterceptPaths);
    }
    
}
