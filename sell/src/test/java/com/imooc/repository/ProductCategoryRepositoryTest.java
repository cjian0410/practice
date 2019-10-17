package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author: JYZ
 * @version: 2019/7/31
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository repository;

	@Test
	public void findOneTest(){
		ProductCategory productCategory = repository.findById(1).get();
		System.out.println(productCategory);
	}
	@Test
	public void saveTest(){
//		ProductCategory productCategory = new ProductCategory();
//		productCategory.setCategoryName("ceshi2");
//		productCategory.setCategoryType(2);
//		repository.save(productCategory);
	}

	@Test
	public void findByCategoryTypeInTest(){
		List<Integer> list = Arrays.asList(2,3,4);
		List<ProductCategory> result = repository.findByCategoryTypeIn(list);
		Assert.assertNotEquals(0,result.size());

	}

}