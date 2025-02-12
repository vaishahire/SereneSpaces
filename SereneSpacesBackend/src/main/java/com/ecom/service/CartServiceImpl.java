package com.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.CartDao;
import com.ecom.dao.CartProductDao;
import com.ecom.dao.ProductDao;
import com.ecom.dao.UserDao;
import com.ecom.dto.CartProductDetailsDto;
import com.ecom.pojo.Cart;
import com.ecom.pojo.CartProduct;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;

@Service
@Transactional
public class CartServiceImpl implements CartService {

		 @Autowired
		 private CartDao cartDao;

	    @Autowired
	    private ProductDao productDao;

	    @Autowired
	    private CartProductDao cartProductDao;

	    @Autowired
	    private UserDao userDao;
	    
	    
	    public Cart getOrCreateCart(Long userId) {
	        User user = userDao.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User  not found"));

	        // Check if the user already has a cart
	        Cart existingCart = cartDao.findByUser(user);
	        if (existingCart!=null) {
	            return existingCart;
	        } else {
	            // Create a new cart
	            Cart newCart = new Cart();
	            newCart.setUser(user);
	            return cartDao.save(newCart);
	        }
	    }

	    public String addProductToCart(Long userId, Long productId, Integer quantity) {
	        Cart cart = getOrCreateCart(userId);
	        Product product = productDao.findByProdId(productId);

	        if(product == null)
	        	new RuntimeException("Product not found");
	        // Check if the selected quantity exceeds the available quantity
	        if (quantity > product.getProdQty()) {
	            return "Requested quantity exceeds available quantity. Available: " + product.getProdQty();
	        }

	        // Find existing CartProduct
	        CartProduct existingCartProduct = cartProductDao.findByCartAndProduct(cart, product);

	        if (existingCartProduct != null) {
	            // Update quantity if it exists
	            existingCartProduct.setSelectedQty(existingCartProduct.getSelectedQty() + quantity);
	            cartProductDao.save(existingCartProduct);
	        } else {
	            // Add new product to cart
	            CartProduct cartProduct = new CartProduct();
	            cartProduct.setCart(cart);
	            cartProduct.setProduct(product);
	            cartProduct.setSelectedQty(quantity);
	            cartProductDao.save(cartProduct);
	        }

	        return "Product added to cart successfully.";
	    }
	    
	    // Remove product from cart
	    	public String removeProductFromCart(Long userId, Long productId) {
	    	    Cart cart = getOrCreateCart(userId);
	    	    Product product = productDao.findById(productId)
	    	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    	    // Find the cart product
	    	    CartProduct cartProduct = cartProductDao.findByCartAndProduct(cart, product);
	    	    
	    	    if (cartProduct != null) {
	    	    	cartProductDao.delete(cartProduct);
	    	        return "Product removed from cart successfully.";
	    	    } else {
	    	        return "Product not found in the cart.";
	    	    }
	    }

	    	
	    	@Override
	    	public List<CartProductDetailsDto> getCartProducts(Long userId) {
	    	    // Get or create the cart for the user
	    	    Cart cart = getOrCreateCart(userId);
	    	    
	    	    // Get the list of CartProduct associated with the cart
	    	    List<CartProduct> cartProducts = cart.getCartProducts();
	    	    
	    	    if (cartProducts == null) {
	    	        cartProducts = new ArrayList<>(); // Fallback to empty list
	    	    }
	    	    
	    	    // Map CartProduct to CartProductDetailsDTO
	    	    List<CartProductDetailsDto> cartProductDetails = new ArrayList<>();
	    	    for (CartProduct cartProduct : cartProducts) {
	    	        Product product = cartProduct.getProduct(); // Fetch the associated product
	    	        CartProductDetailsDto detailsDTO = new CartProductDetailsDto(
	    	        	product.getProdId(),
	    	            product.getProdName(),
	    	            product.getProdPrice(),
	    	            cartProduct.getSelectedQty(),
	    	            product.getProdImage(),
	    	            product.getProdDescp()
	    	        );
	    	        cartProductDetails.add(detailsDTO);
	    	    }
	    	    
	    	    return cartProductDetails;
	    	}

			@Override
			public void deleteCartItemsByUserId(Long userId) {
				 // Find the Cart by userId (assuming Cart has a userId field)
				User user = userDao.findById(userId)
		                .orElseThrow(() -> new RuntimeException("User  not found"));

		        Cart cart = cartDao.findByUser(user);
		        
		        if (cart != null) {
		            // Deleting the Cart will also delete the associated CartItems due to cascading
		        	cartDao.delete(cart);
		        }
			}

	   
}
