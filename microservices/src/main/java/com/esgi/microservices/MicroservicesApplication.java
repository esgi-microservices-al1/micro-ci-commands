package com.esgi.microservices;

<<<<<<< HEAD
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRabbit
=======
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
public class MicroservicesApplication {
    @RequestMapping("/")
    public  String hello(){
        return "  Hello the word !!  ";
    }
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesApplication.class, args);
    }

<<<<<<< HEAD
=======
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedHeaders("application/json")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129

}
