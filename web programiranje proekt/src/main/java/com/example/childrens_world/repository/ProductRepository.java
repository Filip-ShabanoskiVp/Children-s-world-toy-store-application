package com.example.childrens_world.repository;

import com.example.childrens_world.model.Product;
import com.example.childrens_world.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByNameLike(String name);

}
