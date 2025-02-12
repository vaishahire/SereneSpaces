package com.ecom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.dto.ProductDto;
import com.ecom.pojo.Product;

public interface ProductService {
	
	List<Product> getProductsByCategoryIds(List<Long> categoryIds);

	String addProduct(ProductDto productdto);

	Product updateProduct(Long id, ProductDto productDto);

	boolean deleteProduct(Long id);

	Product getProductById(Long id);

	List<Product> getAllProducts();

	void saveProductWithImage(ProductDto productDto, MultipartFile imageFile) throws IOException;

	List<Product> getProductsByCategoryId(Long categoryId);
	

}
