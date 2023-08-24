package com.PostInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.util.Properties;

@Configuration
@SpringBootApplication
@EnableSwagger2
public class PostInventoryApplication {


	public static void main(String[] args) {
		SpringApplication.run(PostInventoryApplication.class, args);

		//TODO: why log4j2 cant read .xml file ??
		Properties properties = new Properties();
		properties.setProperty("log4j.configurationFile", "../../../resources/log4j2.xml");
		System.setProperties(properties);
	}
}
