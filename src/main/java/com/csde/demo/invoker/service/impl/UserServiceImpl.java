package com.csde.demo.invoker.service.impl;
import com.csde.demo.invoker.component.OrderClientHystrix;
import com.csde.demo.invoker.feign.OrderFeignClient;
import com.csde.demo.invoker.pojo.vo.OrderVo;
import com.csde.demo.invoker.service.UserService;
import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     *  //设置熔断
     *             @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
     *             //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
     *             @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
     *             //休眠时间窗
     *             @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
     *             //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
     *             @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40")
     *
     * ————————————————
     * 版权声明：本文为CSDN博主「蓝胖子的白日梦」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/butterBallj/article/details/81323918
     * @return
     */
    @Override
    @HystrixCommand(commandKey = "orderService-getOrder",fallbackMethod = "orderServiceFallback")
    public OrderVo getUserOrder() {

        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        String url="https://www.baidu.c1om/";
        ResponseEntity<String> responseEntity=   restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        log.info("statuscode:{} body:{}",responseEntity.getStatusCode(),responseEntity.getBody());
        url="http://localhost:21001/order/100";
        ResponseEntity<OrderVo> orderVoResponseEntity= restTemplate.exchange(url, HttpMethod.GET, requestEntity, OrderVo.class);
        return orderVoResponseEntity.getBody();

    }

//Object ... objects
    public OrderVo orderServiceFallback() {

        OrderVo orderVo=new OrderVo();
        orderVo.setId("");
        orderVo.setStatus(0);
        orderVo.setCustomer("fail error");
        return orderVo;

    }


    @Override
    public OrderVo getAdminOrder(String id){
        return orderFeignClient.getOrder(id);
    }
}