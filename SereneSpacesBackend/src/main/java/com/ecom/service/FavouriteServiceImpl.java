package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.FavouriteDao;
import com.ecom.dao.ProductDao;
import com.ecom.dao.UserDao;
import com.ecom.dto.FavouriteDto;
import com.ecom.pojo.Favourite;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;

@Service
@Transactional
public class FavouriteServiceImpl implements FavouriteService{

    @Autowired
     FavouriteDao favouriteDao;

    @Autowired
     ProductDao productDao;

    @Autowired
     UserDao userDao;

    @Override
    public String addFavourite(FavouriteDto favouriteDto) {
        Product product = productDao.findById(favouriteDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + favouriteDto.getProductId()));

        User user = userDao.findById(favouriteDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + favouriteDto.getUserId()));

        Favourite favourite = new Favourite();
        favourite.setProducts(product);
        favourite.setUser(user);

        favouriteDao.save(favourite);
        return "Favourite added successfully!";
    }

    @Override
    public boolean deleteFavourite(Long favId) {
        if (favouriteDao.existsById(favId)) {
            favouriteDao.deleteById(favId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Favourite getFavouriteById(Long favId) {
        return favouriteDao.findById(favId).orElse(null);
    }

	public List<Favourite> getFavoritesByUserId(Long userId) {
		User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		return favouriteDao.findByUser(user);
	}

	public Favourite findByUserAndProduct(Long userId, Long productId) {
		 Product product = productDao.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

	        User user = userDao.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		return favouriteDao.findByUserAndProducts(user,product);
	}
}
