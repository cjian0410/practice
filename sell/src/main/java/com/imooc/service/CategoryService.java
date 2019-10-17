package com.imooc.service;

import com.imooc.dataobject.ProductCategory;

import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/7/31
 * @description:
 */
public interface CategoryService {
	ProductCategory findByCategoryId(Integer categoryId);

	List<ProductCategory> findAll();

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	ProductCategory saveOrUpdate(ProductCategory productCategory);
}
