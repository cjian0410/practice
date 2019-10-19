package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.OrderMasterService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;

/**
 * @author: JYZ
 * @version: 2019/10/18
 * @description:
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
		String orderId = KeyUtil.genUniqueKey();
		//1.查询商品（数量，价格）
		for (OrderDetail orderDetail:orderDTO.getOrderDetailList()) {
			ProductInfo productInfo = productService.findById(orderDetail.getProductId());
			if(productInfo==null){
				throw new SellException(ResultEnum.product_not_exist);
			}
			//2.计算总价
			orderAmout=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmout);

			//订单详情入库
			BeanUtils.copyProperties(orderDTO,orderDetail);
			BeanUtils.copyProperties(productInfo,orderDetail);
			orderDetail.setOrderId(orderId);
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetailRepository.save(orderDetail);
		}

		//3.写入订单数据库（OrderMaster和OrderDetail）
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO,orderMaster);
		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmout);
		orderMasterRepository.save(orderMaster);

		//4.扣库存
//		List<CartDTO> cartDTOS = new ArrayList<>();
		productService.decreaseStock(orderDTO.getOrderDetailList().stream().map(e->
					new CartDTO(e.getProductId(),e.getProductQuantity())
		).collect(Collectors.toList()));
		return orderDTO;
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
