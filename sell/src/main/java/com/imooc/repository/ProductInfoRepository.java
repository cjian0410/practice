package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/9/12
 * @description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
	List<ProductInfo> findByProductStatus(Integer productStatus);
}
