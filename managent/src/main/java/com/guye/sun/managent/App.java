package com.guye.sun.managent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/**
 * 主程序启动类
 */
@EnableScheduling
//@EnableSwagger2Doc
@EnableSwagger2
@ServletComponentScan   /*自动扫描带有过滤器注解的包*/
@SpringBootApplication
@ImportResource("classpath:dubbo/dubbo-controller.xml") //加载xml配置文件
@ComponentScan("com.guye.sun.managent")
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(App.class, args);
        SpringContextUtil.setApplicationContext(context);

    }

    static public class SpringContextUtil {

        private static ApplicationContext applicationContext;

        public static ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        private static void setApplicationContext(ApplicationContext applicationContext) {
            SpringContextUtil.applicationContext = applicationContext;
        }

        public static Object getBean(String name) {
            return applicationContext.getBean(name);
        }

        public static <T> T getBean(Class<T> requiredType) {
            return applicationContext.getBean(requiredType);
        }
    }

}