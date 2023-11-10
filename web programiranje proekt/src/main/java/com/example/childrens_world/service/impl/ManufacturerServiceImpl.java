package com.example.childrens_world.service.impl;

import com.example.childrens_world.model.Manufacturer;
import com.example.childrens_world.model.exceptions.ManufacturerNotFoundException;
import com.example.childrens_world.repository.ManufacturerRepository;
import com.example.childrens_world.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManufacturerServiceImpl implements ManufacturerService  {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findAllById(Long id) {
        return this.manufacturerRepository.findById(id)
                .orElseThrow(()-> new ManufacturerNotFoundException(id));
    }

    @Override
    public Manufacturer addNew(Manufacturer manufacturer) {
        return this.manufacturerRepository.save(manufacturer);
    }
}
