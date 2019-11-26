package com.csde.demo.invoker.pojo.vo;


import com.csde.demo.invoker.base.BaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class OrderVo extends BaseVo{

    private static final long serialVersionUID = 4655646015175814197L;
    private String id;
    private int status;
    private String customer;
}