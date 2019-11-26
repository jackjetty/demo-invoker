package com.csde.demo.invoker.service;

import com.csde.demo.invoker.pojo.vo.OrderVo;

public interface UserService{

    OrderVo getUserOrder();

    OrderVo getAdminOrder(String id);
}