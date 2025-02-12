package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.CategoryDao;
import com.ecom.exception.CategoryException;
import com.ecom.pojo.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
		@Autowired
	    private CategoryDao categoryDao;

	  
	    @Override
	    public String addCategory(Category category) {
	    	try {
	        categoryDao.save(category);
	        return "Category Added Successfully";
	    	}catch (Exception e) {
				return "Something went wrong" +e;
			}
	    }

	  
	    @Override
	    public List<Category> getAllCategories() {
	        return categoryDao.findAll();
	    }

	   
	    @Override
	    public Category getCategoryById(Long id) throws CategoryException {
	        return categoryDao.findById(id).orElseThrow(() -> new CategoryException("Category Not Found with ID: " + id));
	    }

	    
	    @Override
	    public String deleteCategory(Long id) throws CategoryException {
	        Category category = categoryDao.findById(id).orElseThrow(() -> new CategoryException("Category Not Found with ID: " + id));
	        categoryDao.delete(category);
	        return "Category Deleted Successfully";
	    }
	    
	    @Override
	    public Category updateCategory(Long id, Category category) {
	      
	        if (categoryDao.existsById(id)) {
	            Category existingCategory = categoryDao.findById(id).orElse(null);
	            if (existingCategory != null) {
	           
	                existingCategory.setCategoryName(category.getCategoryName());
	                categoryDao.save(existingCategory);

	                return existingCategory;
	            }
	        }
	        return null; 
	    }
}
