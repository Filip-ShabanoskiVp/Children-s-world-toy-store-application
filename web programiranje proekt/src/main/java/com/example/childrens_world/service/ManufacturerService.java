package com.example.childrens_world.service;

import com.example.childrens_world.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> findAll();
    Manufacturer findAllById(Long id);
    Manufacturer addNew(Manufacturer manufacturer);
}
