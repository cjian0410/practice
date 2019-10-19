package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/9/12
 * @description:
 */
public interface ProductService {
	ProductInfo findById(String id);

	/**
	 * 查询所有在架商品列表
	 * */
	List<ProductInfo> findUpAll();

	List<ProductInfo> findAll(Pageable pageable);

	ProductInfo save(ProductInfo productInfo);

	//加库存
	void increaseStock(List<CartDTO> cartDTOList);
	//减库存
	void decreaseStock(List<CartDTO> cartDTOList);
}
