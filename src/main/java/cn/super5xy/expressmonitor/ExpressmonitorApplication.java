package cn.super5xy.expressmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.super5xy.expressmonitor.mapper")
public class ExpressmonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpressmonitorApplication.class, args);
    }

}
