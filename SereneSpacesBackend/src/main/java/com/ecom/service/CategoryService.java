package com.ecom.service;

import java.util.List;

import com.ecom.exception.CategoryException;
import com.ecom.pojo.Category;

public interface CategoryService {

	String addCategory(Category category);

	List<Category> getAllCategories();

	Category getCategoryById(Long id) throws CategoryException;

	String deleteCategory(Long id) throws CategoryException;

	Category updateCategory(Long id, Category category);
	
}
