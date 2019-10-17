package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @author: JYZ
 * @version: 2019/10/17
 * @description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
	public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}
