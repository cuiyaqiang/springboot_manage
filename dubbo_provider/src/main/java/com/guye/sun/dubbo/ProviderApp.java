package com.guye.sun.dubbo;

import org.mybatis.spring.annotation.MapperScan;
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

/**
 * 主程序启动类
 */
@SpringBootApplication
@ImportResource({"classpath:dubbo/dubbo-provider.xml"})
@ComponentScan("com.guye.sun.dubbo")
public class ProviderApp extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(ProviderApp.class, args);
        try {
            System.out.println("==============================");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}