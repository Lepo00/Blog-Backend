package it.jac.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "it.jac.blog")
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
