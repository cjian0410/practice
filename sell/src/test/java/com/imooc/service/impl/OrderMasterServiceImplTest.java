package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    private final String buyerOpenid="110110";

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        //账户信息
        orderDTO.setBuyerName("jiancc");
        orderDTO.setBuyerAddress("文一西路");
        orderDTO.setBuyerPhone("18326629676");
        orderDTO.setBuyerOpenid(buyerOpenid);

        //购物车信息
//        List<CartDTO> cartDTOS = new ArrayList<>();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        //商品清单
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderMasterService.create(orderDTO);
        log.info("创建订单 result={}",result);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}