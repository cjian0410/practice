package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * @author: JYZ
 * @version: 2019/10/18
 * @description:
 */
public class SellException extends RuntimeException{
	private Integer code;

	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code=resultEnum.getCode();
	}
}
