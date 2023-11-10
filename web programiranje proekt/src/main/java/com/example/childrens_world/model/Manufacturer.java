package com.example.childrens_world.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @NotNull
    String Name;

    @NotNull
    String Location;

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer")
    List<Product> products;

    public Manufacturer() {
    }

    public Manufacturer(String name, String location) {
        Name = name;
        Location = location;
    }
}
