package com.imooc.enums;

import lombok.Getter;

/**
 * @author: JYZ
 * @version: 2019/10/16
 * @description:
 */
@Getter
public enum PayStatusEnum {
	WAIT(0,"等待支付"),
	SUCCESS(1,"已支付"),;

	private Integer code;

	private String message;

	PayStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
