package com.userManagement.app.test.cart.ervice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.userManagement.app.cart.dto.CartItemDto;
import com.userManagement.app.cart.repository.CartItemRepository;
import com.userManagement.app.cart.repository.CartRepository;
import com.userManagement.app.cart.repository.ItemRepository;
import com.userManagement.app.cart.service.CartService;
import com.userManagement.app.cart.service.impl.CartServiceImpl;
import com.userManagement.app.enums.CrudType;
import com.userManagement.app.inventory.entity.Cart;
import com.userManagement.app.inventory.entity.CartItems;
import com.userManagement.app.inventory.entity.Item;
import com.userManagement.app.user.entity.User;
import com.userManagement.app.user.repository.UserDao;
import com.userManagement.app.user.service.UserService;

class CartItem {

	@Mock
	CartRepository cartRepository;

	@Mock
	CartItemRepository cartItemRepository;

	@Mock
	ItemRepository itemRepository;

	@Mock
	UserDao userDao;
	

	@InjectMocks
	CartServiceImpl CartService;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveCartItem() {

		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setAmount(1000);
		cartItemDto.setCrudType(CrudType.SAVE);
		cartItemDto.setItemId(1);

		User user = new User();
		user.setId(1);

		Cart cart = new Cart();
		cart.setId(1);

		CartItems cartItems = new CartItems();
		BeanUtils.copyProperties(cartItemDto, cartItems);
		
		Item item = new Item();
		item.setId(1);
		
		// mock user
		when(userDao.findByUserName(anyString())).thenReturn(user);
		when(cartRepository.findByUser(anyLong())).thenReturn(null);
		when(cartRepository.save(any(Cart.class))).thenReturn(cart);
		when(cartItemRepository.save(any(CartItems.class))).thenReturn(cartItems);
		when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));
		
		CartItemDto cd = CartService.saveCartItem(cartItemDto);
		
		assertNotNull(cd);
		

	}

}
