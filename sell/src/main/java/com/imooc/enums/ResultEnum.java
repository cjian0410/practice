package com.imooc.enums;

import lombok.Getter;

/**
 * @author: JYZ
 * @version: 2019/10/18
 * @description:
 */
@Getter
public enum ResultEnum {
	PRODUCT_NOT_EXIST(10,"商品不存在"),
	PRODUCT_STOCK_ERROR(11,"商品库存异常"),
	ORDER_NOT_EXIST(12,"订单不存在"),
	ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
	ORDER_STATUS_ERROR(14,"订单状态不正确"),
	ORDER_UPDATE_FAIL(15,"订单更新失败"),
	;
	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
