package com.ecom.service;

import java.util.List;

import com.ecom.dto.FavouriteDto;
import com.ecom.pojo.Favourite;

public interface FavouriteService {

	String addFavourite(FavouriteDto favouriteDto);
	
	boolean deleteFavourite(Long favId);

	Favourite getFavouriteById(Long favId);
	
	 List<Favourite> getFavoritesByUserId(Long userId);

	 Favourite findByUserAndProduct(Long userId, Long productId);
}
