package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: JYZ
 * @version: 2019/10/17
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

	@Autowired
	private OrderDetailRepository repository;

	@Test
	public void saveTest(){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId("1");
		orderDetail.setDetailId("1");
		orderDetail.setProductIcon("http://xxx.jpg");
		orderDetail.setProductId("1");
		orderDetail.setProductName("培根鸡腿汉堡");
		orderDetail.setProductPrice(new BigDecimal(3.2));
		orderDetail.setProductQuantity(20);
		repository.save(orderDetail);
	}

	@Test
	public void findByOrderId() {
		List<OrderDetail> orderDetails = repository.findByOrderId("1");
		orderDetails.stream().forEach(e-> System.out.println(e.getProductName()));
	}
}