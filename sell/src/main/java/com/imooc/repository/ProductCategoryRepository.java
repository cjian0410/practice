package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/7/31
 * @description:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
