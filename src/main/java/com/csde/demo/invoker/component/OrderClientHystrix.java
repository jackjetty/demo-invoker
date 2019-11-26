package com.csde.demo.invoker.component;

import com.csde.demo.invoker.feign.OrderFeignClient;
import com.csde.demo.invoker.pojo.vo.OrderVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class OrderClientHystrix implements OrderFeignClient{


    @Override
    public OrderVo getOrder(@PathVariable("id") String id) {
        OrderVo orderVo=new OrderVo();
        orderVo.setId("");
        orderVo.setStatus(0);
        orderVo.setCustomer("fail error");
        return orderVo;
    }
}