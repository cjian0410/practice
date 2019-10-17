package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: JYZ
 * @version: 2019/7/31
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
	@Autowired
	private CategoryService categoryService;

	@Test
	public void findOne() throws Exception{
		ProductCategory productCategory = categoryService.findByCategoryId(1);
		System.out.println(productCategory);
	}
	@Test
	public void findAll(){
		List<ProductCategory> categories = categoryService.findAll();
	}
	@Test
	public void findByCategoryTypeIn(){
		List<Integer> list = Arrays.asList(1,3);
		List<ProductCategory> categories = categoryService.findByCategoryTypeIn(list);
	}
	@Test
	public void saveOrUpdate(){
		ProductCategory productCategory = categoryService.findByCategoryId(1);
		productCategory.setCategoryName("金属");
		categoryService.saveOrUpdate(productCategory);
	}
}