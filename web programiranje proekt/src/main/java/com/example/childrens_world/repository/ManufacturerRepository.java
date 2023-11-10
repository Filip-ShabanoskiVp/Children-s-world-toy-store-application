package com.example.childrens_world.repository;

import com.example.childrens_world.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
//    List<Manufacturer> findByLocation(String location);
}
