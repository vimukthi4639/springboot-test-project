package com.userManagement.app.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userManagement.app.inventory.entity.Cart;
import com.userManagement.app.inventory.entity.CartItems;
import com.userManagement.app.user.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	public Cart findByUser(long userId);

}
