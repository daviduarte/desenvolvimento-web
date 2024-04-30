package br.com.camnuvem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.camnuvem.model"})
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.camnuvem.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class ApiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/camera/**")
			.allowedMethods("*")
			//.allowedMethods("POST", "DELETE")
			//.allowedOrigins( "http://www.cliente1.com", 
				//						"http://www.cliente2.com");
			.allowedOrigins("*");
	}

}

