package com.ecom.controller;

import com.ecom.dto.FavouriteDto;
import com.ecom.pojo.Favourite;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;
import com.ecom.service.FavouriteServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavouriteController {

    @Autowired
    private FavouriteServiceImpl favouriteService;

    
   
    @GetMapping("/getfavbyid/{favId}")
    public ResponseEntity<?> getFavouriteById(@PathVariable Long favId) {
        Favourite favourite = favouriteService.getFavouriteById(favId);
        if (favourite != null) {
            return ResponseEntity.status(HttpStatus.OK).body(favourite);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favourite not found with id: " + favId);
        }
    }

   
    @PostMapping("/addfavourite")
    public ResponseEntity<?> addFavourite(@RequestBody FavouriteDto favouriteDto) {
        String responseMessage = favouriteService.addFavourite(favouriteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

   

    
    @DeleteMapping("/deletefavourite/{favId}")
    public ResponseEntity<?> deleteFavourite(@PathVariable Long favId) {
        boolean isDeleted = favouriteService.deleteFavourite(favId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favourite not found with id: " + favId);
        }
    }
    
    
    @GetMapping("/getUserFavorites/{userId}")
    public ResponseEntity<?> getUserFavourites(@PathVariable Long userId) {
        List<Favourite> favourites = favouriteService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favourites);
    }
    
    
    @PostMapping("/togglefavourite")
    public ResponseEntity<?> addRemoveFavourite(@RequestParam Long productId, @RequestParam Long userId) {
        Favourite existingFavorite = favouriteService.findByUserAndProduct(userId, productId);

        if (existingFavorite!=null) {
            // Item already in favorites, so remove it
        	
        	favouriteService.deleteFavourite(existingFavorite.getFavId());
        	
            return ResponseEntity.ok("Removed from favorites");
        } else {
        	
        	favouriteService.addFavourite(new FavouriteDto(productId,userId));
        	
            return ResponseEntity.ok("Added to favorites");
        }
    }
}
