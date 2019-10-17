package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/10/17
 * @description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
	public List<OrderDetail> findByOrderId(String orderId);
}
