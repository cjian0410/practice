package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/7/31
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public ProductCategory findByCategoryId(Integer categoryId) {
		return repository.findById(categoryId).get();
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory saveOrUpdate(ProductCategory productCategory) {
		return repository.save(productCategory);
	}
}
