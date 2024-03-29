package com.imooc.dataobject;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/10/16
 * @description:
 */
@Entity
@Data
@DynamicInsert
public class OrderMaster {
	/** 订单id */
	@Id
	private String orderId;

	/** 买家姓名 */
	private String buyerName;

	/** 买家手机号 */
	private String buyerPhone;

	/** 买家地址 */
	private String buyerAddress;

	/** 买家微信Openid */
	private String buyerOpenid;

	/** 订单总金额 */
	private BigDecimal orderAmount;

	/** 订单状态 默认为0 新下单 */
	private Integer orderStatus=OrderStatusEnum.NEW.getCode();

	/** 支付状态  默认为0  未支付*/
	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;


}
