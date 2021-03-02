package com.PostInventory;

import com.PostInventory.Repositories.PostRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class PostInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostInventoryApplication.class, args);
	}

}
