package com.madd.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.madd"})
@MapperScan(basePackages = {"com.madd.template.mapper"})
@SpringBootApplication
public class TemplateServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateServerApplication.class, args);
    }

}
