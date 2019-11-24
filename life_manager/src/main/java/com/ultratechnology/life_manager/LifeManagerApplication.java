package com.ultratechnology.life_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ultratechnology.life_manager.mapper")
public class LifeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeManagerApplication.class, args);
	}

}
