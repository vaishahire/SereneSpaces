package com.ecom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dao.CategoryDao;
import com.ecom.dao.ProductDao;
import com.ecom.dto.ProductDto;
import com.ecom.pojo.Category;
import com.ecom.pojo.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

		@Autowired
		ProductDao dao;

		@Autowired
		CategoryDao categoryDao;
		
		@Autowired
		CategoryService categoryService;
		
		
		
		@Override
		public String addProduct(ProductDto productdto) {
			 Category category = categoryDao.findById(productdto.getCategoryId())
			            .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productdto.getCategoryId()));

			        Product product = new Product();
			        product.setProdName(productdto.getProdName());
			        product.setProdPrice(productdto.getProdPrice());
			        product.setProdQty(productdto.getProdQty());
			        product.setProdDescp(productdto.getProdDescp());
			        product.setCategory(category); 

			       
			        dao.save(product); 

			        return "Product added successfully!";
			
		}
		
//		public Product saveProductWithImage(ProductDto productdto) throws IOException {
//			
//			Category category = categoryDao.findById(productdto.getCategoryId())
//		            .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productdto.getCategoryId()));
//
//			
//	        // Create a new product instance
////	        Product product = new Product(
////	                productDTO.getProdName(),
////	                productDTO.getProdPrice(),
////	                productDTO.getProdQty(),
////	                productDTO.getProdDescp(),
////	                productDTO.getCategoryId()
////	        );
//
//			Product product = new Product();
//	        product.setProdName(productdto.getProdName());
//	        product.setProdPrice(productdto.getProdPrice());
//	        product.setProdQty(productdto.getProdQty());
//	        product.setProdDescp(productdto.getProdDescp());
//	        product.setCategory(category); 
//	        
//	        // If there is an image, set it in the product entity
//	        if (productdto.getImageFile() != null && !productdto.getImageFile().isEmpty()) {
//	            product.setProdImage(productdto.getImageFile().getBytes());
//	        }
//
//	        // Save the product to the database
//	        return dao.save(product);
//	    }
//		
		
		
		
		public void saveProductWithImage(ProductDto productDto, MultipartFile imageFile) throws IOException {
	        // Convert the MultipartFile to a byte array (this will be stored as a BLOB)
	        byte[] imageBytes = imageFile.getBytes();

	        Category category = categoryDao.findById(productDto.getCategoryId())
	            .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDto.getCategoryId()));
//
	        		
	        // Create a new Product entity and map the DTO to the entity
	        Product product = new Product();
	        product.setProdName(productDto.getProdName());
	        product.setProdPrice(productDto.getProdPrice());
	        product.setProdQty(productDto.getProdQty());
	        product.setProdDescp(productDto.getProdDescp());
	        product.setCategory(category);
	        product.setProdImage(imageBytes); // Set the image as a byte array

	        // Save the product to the database
	        dao.save(product);
	    }
		
		
		 @Override
		 public List<Product> getProductsByCategoryId(Long categoryId) {
		        return dao.findByCategory_CategoryId(categoryId);
		    }
		 
		 @Override
		    public Product updateProduct(Long id, ProductDto productDto) {
		       
		        Product existingProduct = dao.findById(id).orElse(null);

		        if (existingProduct != null) {
		           
		            existingProduct.setProdName(productDto.getProdName());
		            existingProduct.setProdPrice(productDto.getProdPrice());
		            existingProduct.setProdQty(productDto.getProdQty());
		            existingProduct.setProdDescp(productDto.getProdDescp());

					Category category = categoryDao.findById(productDto.getCategoryId())
				            .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDto.getCategoryId()));
					
					if (category != null) {
		                existingProduct.setCategory(category);
		            } else {
		                return null;  
		            }
 
		            return dao.save(existingProduct);
		        } else {
		         
		            return null;
		        }
		    }
		 
		 @Override
		    public boolean deleteProduct(Long id) {
		     
		        if (dao.existsById(id)) {
		           
		            dao.deleteById(id);
		            return true;
		        } else {
		          
		            return false;
		        }
		    }
		 
		 @Override
		    public Product getProductById(Long id) {
		        return dao.findById(id).orElse(null); 
		    }
		 
		 @Override
		    public List<Product> getAllProducts() {
		        return dao.findAll();  
		    }

		@Override
		public List<Product> getProductsByCategoryIds(List<Long> categoryIds) {
			return dao.findByCategoryCategoryIdIn(categoryIds);
		}
}
