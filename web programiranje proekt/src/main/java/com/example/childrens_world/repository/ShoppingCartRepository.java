package com.example.childrens_world.repository;

import com.example.childrens_world.model.ShoppingCart;
import com.example.childrens_world.model.enumerations.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    boolean existsByUserUsernameAndStatus(String username, CartStatus status);
    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, CartStatus status);
}
