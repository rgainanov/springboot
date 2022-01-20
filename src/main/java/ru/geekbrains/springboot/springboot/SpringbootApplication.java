package ru.geekbrains.springboot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class SpringbootApplication {

    //    TODO Authentication
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
