package com.saiwill.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@PropertySource(ignoreResourceNotFound=true,value="classpath:business-${spring.profiles.active}.properties")
@ImportResource(value = {"classpath:application-mybatis.xml"})
@MapperScan(basePackages = "com.saiwill.business.mapper")
public class Application {
}
