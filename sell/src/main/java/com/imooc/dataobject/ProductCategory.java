package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author: JYZ
 * @version: 2019/7/31
 * @description:
 */
@Data
@Entity
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	/**类目名称*/
	private String categoryName;
	/**类目编号*/
	private Integer categoryType;

}
