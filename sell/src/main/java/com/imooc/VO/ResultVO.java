package com.imooc.VO;

import lombok.Data;

/**
 * http返回的最外层对象
 * @author: JYZ
 * @version: 2019/10/14
 * @description:
 */
@Data
public class ResultVO<T> {
	/** 错误码 */
	private Integer code;

	/** 提示信息 */
	private String msg;

	/** 具体内容 */
	private T data;

}
