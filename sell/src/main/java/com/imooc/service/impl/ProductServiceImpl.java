package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author: JYZ
 * @version: 2019/9/12
 * @description:
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoRepository repository;

	@Override
	public ProductInfo findById(String id) {
		return repository.getOne(id);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public List<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll();
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}

	@Override
	public void increaseStock(List<CartDTO> cartDTOList) {

	}

	@Override
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO:cartDTOList) {
			ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
			if(productInfo==null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if(result<0){
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
	}
}
