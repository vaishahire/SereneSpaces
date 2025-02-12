package com.ecom.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.exception.CategoryException;
import com.ecom.pojo.Category;
import com.ecom.service.CategoryService;


@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    CategoryService service;

   
    @GetMapping("/getcategories")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategories());
    }

   
    @PostMapping("/addcategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCategory(category));
    }

   
    @GetMapping("/getcategory/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            Category category = service.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (CategoryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

   
    @PutMapping("/updatecategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = service.updateCategory(id, category);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with id: " + id);
        }
    }

   
    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            String result = service.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (CategoryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
