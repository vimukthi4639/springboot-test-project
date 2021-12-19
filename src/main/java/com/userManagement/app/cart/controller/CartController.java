package com.userManagement.app.cart.controller;

import javax.validation.Valid;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userManagement.app.cart.dto.CartItemDto;
import com.userManagement.app.cart.service.CartService;

@RestController
@Path("/cart")
public class CartController {

	@Autowired
	CartService CartService;

	@PostMapping("/mangeCart")
	public CartItemDto manageCart(@Valid @RequestBody CartItemDto cartItemDto) {
		CartItemDto c = CartService.saveCartItem(cartItemDto);
		return c;
	}

}
