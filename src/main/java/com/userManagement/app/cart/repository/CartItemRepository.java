package com.userManagement.app.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userManagement.app.inventory.entity.CartItems;
@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long>{

}
