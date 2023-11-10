package com.example.childrens_world.model;



import com.example.childrens_world.model.enumerations.Category;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private LocalDateTime localDateTime = LocalDateTime.now();

    private String description;

    @Column(columnDefinition = "TEXT")
//    @Lob
//    @Column(name = "pImage",length = 999999999)
    private String productImage;

    @NotNull
    @Min(value = 1,message = "Quantity must not be less then 1")
    private int quantity;


    @NotNull
    @Min(value = 1,message = "Price must not be less then 1")
    private float cost;

    @NotNull
    String Color;

    @Enumerated(EnumType.STRING)
    Category category;


    @NotNull(message = "Manufacturer must not be null")
    @ManyToOne
    private Manufacturer manufacturer;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<ShoppingCart>shoppingCarts;

    public Product(){}

    public Product(Long id, String name, LocalDateTime localDateTime, String description,
                   int quantity, float cost, Manufacturer manufacturer) {
        this.name = name;
        this.localDateTime = localDateTime;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.manufacturer = manufacturer;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
