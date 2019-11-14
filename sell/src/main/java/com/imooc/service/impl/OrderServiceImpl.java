package com.imooc.service.impl;

import ch.qos.logback.classic.filter.ThresholdFilter;
import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: JYZ
 * @version: 2019/10/18
 * @description:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

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
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
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
		OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
		if(orderMaster==null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster,orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());
		return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
	}

	@Override
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		//先判断订单状态
		if (orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.info("【取消订单】,订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//修改订单状态
		orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if(updateResult==null){
			log.error("【取消订单失败】,orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		//返还库存

		//如果已支付，需要退款
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
