package com.zhiyi.medicinebox.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:config/application.properties", "classpath:config/system.properties"})
@MapperScan("com.hy.hicash.api.infrastructure.persistence.mapper")
public class MedicineBoxAPIApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MedicineBoxAPIApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MedicineBoxAPIApplication.class);
    }
}
