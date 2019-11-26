package com.csde.demo.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.csde"})
public class InvokerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(InvokerApplication.class, args);

    }
}