package com.csde.demo.invoker.controller;

import com.csde.demo.invoker.pojo.vo.OrderVo;
import com.csde.demo.invoker.service.UserService;
import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.security.SecureRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "user/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderVo getUserOrder(HttpServletRequest request, HttpServletResponse response){


         return userService.getUserOrder();
    }


    @GetMapping(value = "admin/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderVo getAdminOrder(HttpServletRequest request, HttpServletResponse response){
        SecureRandom random = new SecureRandom();
        String id= String.valueOf(random.nextInt(500));
        return userService.getAdminOrder(id);
    }


}