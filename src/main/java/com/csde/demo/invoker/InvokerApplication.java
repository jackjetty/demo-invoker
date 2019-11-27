package com.csde.demo.invoker;

import com.csde.demo.invoker.pojo.vo.OrderVo;
import com.csde.demo.invoker.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@Slf4j
@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.csde"})
public class InvokerApplication  implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private Gson gson;


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(InvokerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        OrderThread orderThread;

        for(int i=600;i<=630;i++){
            orderThread=new OrderThread(userService,String.valueOf(i));
            new Thread(orderThread).start();
            //orderVo=userService.getAdminOrder();
            //
        }
    }


}
@Slf4j
class OrderThread implements  Runnable{

    private UserService userService;
    private String id;

    public OrderThread(UserService userService,String id){
        this.userService=userService;
        this.id=id;
    }
    @Override
    public void run() {
        OrderVo orderVo=userService.getAdminOrder(id);
        log.info("result:{}",new Gson().toJson(orderVo));
    }
}