package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Favourite;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;

public interface FavouriteDao extends JpaRepository<Favourite, Long>{

	List<Favourite> findByUser(User user);
	Favourite findByUserAndProducts(User user,Product product);
}
