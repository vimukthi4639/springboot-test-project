package com.userManagement.app.cart.service.impl;

import java.beans.Beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userManagement.app.cart.dto.CartItemDto;
import com.userManagement.app.cart.repository.CartItemRepository;
import com.userManagement.app.cart.repository.CartRepository;
import com.userManagement.app.cart.repository.ItemRepository;
import com.userManagement.app.cart.service.CartService;
import com.userManagement.app.enums.CrudType;
import com.userManagement.app.exception.ValidationException;
import com.userManagement.app.inventory.entity.Cart;
import com.userManagement.app.inventory.entity.CartItems;
import com.userManagement.app.inventory.entity.Item;
import com.userManagement.app.user.entity.User;
import com.userManagement.app.user.repository.UserDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserDao userDao;

	@Override
	public CartItemDto saveCartItem(CartItemDto cartItemDto) {

		// find user
		User user = userDao.findByUserName("VK");

		// find user cart
		Cart cart = cartRepository.findByUser(user.getId());

		if (cart == null) {
			// save new cart
			Cart newCart = new Cart();
			newCart.setUser(user);

			cart = cartRepository.save(newCart);
		}

		CartItems crdCartItem = null;

		// save or update cart item
		if (cartItemDto.getCrudType() == CrudType.SAVE) {
			// save new cart item
			CartItems cartItems = new CartItems();
			BeanUtils.copyProperties(cartItemDto, cartItems);
			cartItems.setCart(cart);
			// find item
			Item item = itemRepository.findById(cartItemDto.getCartItemId()).orElseThrow();
			cartItems.setItem(item);
			crdCartItem = cartItemRepository.save(cartItems);
		} else if (cartItemDto.getCrudType() == CrudType.UPDATE) {
			// find cart item by id
			CartItems cartItem = cartItemRepository.findById(cartItemDto.getCartItemId()).orElseThrow();
			BeanUtils.copyProperties(cartItemDto, cartItem);

			crdCartItem = cartItemRepository.save(cartItem);

		} else if (cartItemDto.getCrudType() == CrudType.DELETE) {
			CartItems cartItem = cartItemRepository.findById(cartItemDto.getCartItemId()).orElseThrow();
			cartItemRepository.delete(cartItem);
		}

		CartItemDto cr = new CartItemDto();
		if (crdCartItem != null) {
			BeanUtils.copyProperties(crdCartItem, cr);
		}

		return cr;

	}

}
