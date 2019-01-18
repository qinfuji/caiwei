package com.saiwill.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@PropertySource(value={"classpath:business-${spring.profiles.active}.properties","classpath:application.properties"})
@MapperScan(basePackages = "com.saiwill.business.mapper")
@ImportResource("classpath:application-business-context.xml")
public class Application {
}
