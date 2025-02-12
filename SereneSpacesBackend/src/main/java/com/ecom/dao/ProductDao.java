package com.ecom.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.pojo.Product;
import com.ecom.pojo.User;

@Service
@Transactional
public interface ProductDao extends JpaRepository<Product, Long>{
	 List<Product> findByCategory_CategoryId(Long categoryId);

	Product findByProdId(Long prodId);

	List<Product> findByCategoryCategoryIdIn(List<Long> categoryIds);

	
	
}
