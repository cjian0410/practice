package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.OrderMasterService;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author: JYZ
 * @version: 2019/10/18
 * @description:
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

	@Autowired
	private ProductService productService;

	@Override
	public OrderDTO create(OrderDTO orderDTO) {
		BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
		//1.查询商品（数量，价格）
		for (OrderDetail orderDetail:orderDTO.getOrderDetailList()) {
			ProductInfo productInfo = productService.findById(orderDetail.getProductId());
			if(productInfo==null){
				throw new SellException(ResultEnum.product_not_exist);
			}
			//2.计算总价
			orderAmout=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmout);
		}

		//3.写入订单数据库（OrderMaster和OrderDetail）

		//4.扣库存
		return null;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		return null;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		return null;
	}

	@Override
	public OrderDTO cancel(OrderDTO orderDTO) {
		return null;
	}

	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		return null;
	}

	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		return null;
	}
}
