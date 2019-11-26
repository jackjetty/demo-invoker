package com.csde.demo.invoker.feign;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.csde.demo.invoker.component.OrderClientHystrix;
import com.csde.demo.invoker.pojo.vo.OrderVo;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "order-client", url = "http://localhost:21001",fallback = OrderClientHystrix.class)
public interface OrderFeignClient{

    @RequestMapping(value = "/order/{id}",
            method = GET,
            consumes = APPLICATION_JSON_UTF8_VALUE)
    OrderVo getOrder(@PathVariable("id") String id);

}