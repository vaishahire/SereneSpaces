package com.ecom.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity // - required in earlier spring sec versions -enabled by default
@Configuration // equivalent to bean config xml file
@AllArgsConstructor
public class SecurityConfig {

	
	private PasswordEncoder encoder;
	private CustomJwtAuthenticationFilter jwtFilter;

	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
	    http.csrf(customizer -> customizer.disable())  // Disable CSRF protection
	        .authorizeHttpRequests(request -> request
	            // Permit all these paths without authentication
	            .requestMatchers(
	                    "/products/getallproduct", 
	                    "/user/signup", 
	                    "/authusers/signin", 
	                    "/products/getproductsbycategory",
	                    "/products/getproductbyid",
	                    "/category/getcategories",
	                    "/category/getcategory",
	                    "/orders/getOrders",
	                    "/v*/api-doc*/**", 
	                    "/swagger-ui/**")
	                .permitAll()
	            .requestMatchers(HttpMethod.OPTIONS)
	                .permitAll()
	               
	            // Allow access to "USER" role (prefixed with ROLE_)
	            .requestMatchers(
	                    "/user/updateuser", 
	                    "/user/getuser",
	                    "/cart/addCart",
	                    "/cart/removeCart",
	                    "/cart/Cartproducts",
	                    "/favourites/addfavourite",
	                    "/favourites/deletefavourite",
	                    "/favourites/getfavbyid",
	                    "/favourites/getUserFavorites",
	                    "/favourites/togglefavourite",
	                    "/orders/addOrder",
	                    "/payments/addpayment",
	                    "/reviews/addreview",
	                    "/reviews/updatereview",
	                    "/reviews/deletereview",
	                    "/reviews/getreviewbyproduct"
//	                    "/orders/getOrders"
	                   )
	                .hasRole("USER")  // Ensure that 'ROLE_USER' is checked
	            .requestMatchers(
	                    "/products/addproduct", 
	                    "/products/deleteproduct",
	                    "/products/updateproduct",
	                    "category/addcategory",
	                    "category/updatecategory",
	                    "category/deletecategory",
	                    "/user/getuser",
	                    "/user/deleteuser")
	                .hasRole("ADMIN")  // Ensure that 'ROLE_ADMIN' is checked
	            .anyRequest()
	                .authenticated()  // All other requests require authentication
	        )
	        // Stateless session management (since you're using JWT)
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        // Add JWT filter before the username-password filter
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	
	// configure AuthMgr as a spring bean
	
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
