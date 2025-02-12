package com.ecom.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.pojo.Category;
import com.ecom.pojo.Product;

@Service
@Transactional
public interface CategoryDao extends JpaRepository<Category, Long>{

}
