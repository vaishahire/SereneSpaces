package com.ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dto.ProductDto;
import com.ecom.pojo.Product;
import com.ecom.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	ProductService service;
	
//	@PostMapping("/addproduct")
//	public ResponseEntity<?> addProduct(@RequestBody ProductDto productdto) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(productdto));
//	}
	
//	public ResponseEntity<?> saveProductWithImage(@RequestBody ProductDto productDTO) {
//        try {
//            Product savedProduct = service.saveProductWithImage(productDTO);
//            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
	
	@PostMapping("/addproduct")
	 public ResponseEntity<?> saveProductWithImage(
	            @ModelAttribute ProductDto productDTO, // Getting the product details from the form
	            @RequestPart MultipartFile imageFile // Getting the image file
	    ) {
	        try {
	            // Call the service layer to handle saving the product with the image
	        	service.saveProductWithImage(productDTO, imageFile);
	            return new ResponseEntity<>("Product and image uploaded successfully", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error uploading product and image", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	
//	@GetMapping("/getproductsbycategory/{categoryId}")
//    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Long categoryId) {
//        List<Product> products = service.getProductsByCategoryId(categoryId);
//        return ResponseEntity.status(HttpStatus.OK).body(products);
//    }
	
	@GetMapping("/getproductsbycategory")
	public ResponseEntity<?> getProductsByCategory(@RequestParam List<Long> categoryIds) {
	    List<Product> products = service.getProductsByCategoryIds(categoryIds);
	    return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	
	 @PutMapping("/updateproduct/{id}")
	    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
	        Product updatedProduct = service.updateProduct(id, productDto);
	        
	        if (updatedProduct != null) {
	            return ResponseEntity.ok(updatedProduct);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
	        }
	    }
	 
	 @DeleteMapping("/deleteproduct/{id}")
	    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
	        boolean isDeleted = service.deleteProduct(id);
	        
	        if (isDeleted) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
	        }
	    }
	 
	  @GetMapping("/getproductbyid/{id}")
	    public ResponseEntity<?> getProductById(@PathVariable Long id) {
	        Product product = service.getProductById(id);

	        if (product != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(product);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
	        }
	    }
	  
	  @GetMapping("/getallproduct")
	    public ResponseEntity<?> getAllProducts() {
	        List<Product> products = service.getAllProducts();
	        
	        if (!products.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.OK).body(products);
	        } else {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No products found");
	        }
	    }
}
