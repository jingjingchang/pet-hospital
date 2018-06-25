package com.zcmzjp.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@MapperScan(basePackages={"com.zcmzjp.wx.mapper"})
public class WxApplication {
	public static void main(String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}
}
