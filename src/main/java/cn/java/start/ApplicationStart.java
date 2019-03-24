
package cn.java.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 只存放spring boot的启动代码<br/>
 */
@SpringBootApplication(scanBasePackages = { "cn.java.**"})
@MapperScan(value = "cn.java.mapper")
public class ApplicationStart {
    public static void main(String[] args) {
        // 启动spring boot
        SpringApplication.run(ApplicationStart.class, args);
    }
}
