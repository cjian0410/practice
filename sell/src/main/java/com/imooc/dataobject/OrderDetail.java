package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author: JYZ
 * @version: 2019/10/16
 * @description:
 */
@Entity
@Data
public class OrderDetail {
	@Id
	private String detailId;

	/** 订单ID */
	private String orderId;

	/** 商品ID */
	private String productId;

	/** 商品名称 */
	private String productName;

	/** 商品价格 */
	private BigDecimal productPrice;

	/** 商品数量 */
	private Integer productQuantity;

	/** 商品图标 */
	private String productIcon;
}
