package com.imooc.enums;

import lombok.Getter;

/**
 * @author: JYZ
 * @version: 2019/10/18
 * @description:
 */
@Getter
public enum ResultEnum {
	product_not_exist(10,"商品不存在"),
	product_stock_error(11,"商品库存异常"),
	;
	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
