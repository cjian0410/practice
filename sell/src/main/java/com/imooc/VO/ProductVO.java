package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/10/14
 * @description:
 */
@Data
public class ProductVO {
	@JsonProperty("name")
	private String categoryName;

	@JsonProperty("type")
	private Integer categoryType;

	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;
}
