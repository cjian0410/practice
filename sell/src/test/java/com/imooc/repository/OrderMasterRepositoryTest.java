package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author: JYZ
 * @version: 2019/10/17
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

	@Autowired
	private OrderMasterRepository repository;

	@Test
	public void saveTest(){
//		OrderMaster orderMaster = new OrderMaster();
//		orderMaster.setOrderId("3");
//		orderMaster.setBuyerName("Uzi");
//		orderMaster.setBuyerPhone("12345678901");
//		orderMaster.setBuyerAddress("mooc");
//		orderMaster.setBuyerOpenid("110110");
//		orderMaster.setOrderAmount(new BigDecimal(4.5));
//		repository.save(orderMaster);
	}

	@Test
	public void findByBuyerOpenid() {
		PageRequest request = PageRequest.of(0, 10);

		Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("110110", request);
		orderMasters.get().forEach(e->
			System.out.println(e.getBuyerName())
		);

	}
}