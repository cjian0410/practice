package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author: JYZ
 * @version: 2019/9/12
 * @description:
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
	@Id
	private String productId;

	/** 名字 */
	private String productName;

	/** 单价 */
	private BigDecimal productPrice;

	/** 库存 */
	private Integer productStock;

	/** 描述 */
	private String productDescription;

	/** 小图 */
	private String productIcon;

	/** 状态， 0正常1下架 */
	private Integer productStatus;

	/** 类目编号 */
	private Integer categoryType;

}
